const express = require('express');
const app = express();
const ssdp = require('node-ssdp').Client;
const client = new ssdp({});
const axios = require('axios');
const http = require('http').Server(app);
const io = require('socket.io')(http);
const Subscription = require('node-upnp-subscription');

let discovered = false;
let address;
let port;
let url;
let upnp;

const path = '/sensors-actuators-1.0-SNAPSHOT';

app.get('/', function (req, res) {
  res.sendFile('./index.html', {
    root: '.'
  });
});

io.on('connection', function (socket) {
  console.log('user connected');

  if (discovered) {
    socket.emit('state', {
      state: 'ready'
    });
  }

  socket.on('message', function (req) {
    app.post('/message', function (req, res) {
      axios.post(`${url}/message`, {
          msg: req.msg
        })
        .then((resp) => {
          console.log(resp);
        })
        .catch((err) => {
          console.error(err);
        });
    });
  });

  socket.on('orientation', function (req) {
    axios.get(`${url}/orientation`)
      .then((resp) => {
        console.log(resp);
      })
      .catch((err) => {
        console.error(err);
      });
  });

  socket.on('ledOn', function (req) {
    console.log(req.color);
    /*
    axios.post(`${url}/ledOn`, {
      red: req.color.red,
      green: req.color.green,
      blue: req.color.blue,
    })
    .then((resp) => {
      // TODO
    })
    .catch((err) => {
      console.error(err);
    });
    */
  });
});

http.listen(8000, function () {
  console.log('listening on *:8000');
});

client.on('notify', function () {
  console.log('Got a notification.')
})

client.on('response', function inResponse(headers, code, rinfo) {
  address = rinfo.address;
  port = rinfo.port;
  url = `http://${address}:${port}${path}`;
  discovered = true;
  io.emit('state', {
    state: 'ready'
  });
  console.log(`EarthService fount at: http://${address}:${port}/`);
  upnp = new Subscription(address, port, path);

  upnp.on('Orientation', function (orientation) {
    console.log(orientation);
  });
});

console.log('ssdp scan started');
client.search('urn:schemas-upnp-org:service:EarthService:1');
// client.search('ssdp:all');

setTimeout(() => {
  console.log(`ssdp scan stopped`);
  client.stop()
}, 5000)