const SSDP = require('node-ssdp').Server;
const server = new SSDP({
  sourcePort: 1900
});
const devices = [];

server.addUSN('upnp:rootdevice');
server.addUSN('urn:schemas-upnp-org:device:MediaServer:1');
server.addUSN('urn:schemas-upnp-org:service:ContentDirectory:1');
server.addUSN('urn:schemas-upnp-org:service:ConnectionManager:1');

server.on('advertise-alive', function (headers) {
  // Expire old devices from your cache.
  // Register advertising device somewhere (as designated in http headers heads)
  const ip = JSON.stringify(headers.HOST).split(':')[0];
  if (devices.indexOf(ip) === -1) {
    devices.push(ip);
  }
});

server.on('advertise-bye', function (headers) {
  // Remove specified device from cache.
  const index = devices.indexOf(ip);
  if (index !== -1) {
    device.splice(index);
  }
});

// start the server
server.start();
console.log('server running...')

process.on('exit', function () {
  console.log('server stopped');
  server.stop() // advertise shutting down and stop listening
});