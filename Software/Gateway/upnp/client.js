const ssdp = require('node-ssdp').Client;
const client = new ssdp({});

let nbResponses = 0;
let address;
let port;

client.on('notify', function () {
  // console.log('Got a notification.')
})

client.on('response', function inResponse(headers, code, rinfo) {
  nbResponses += 1;
  /*console.log(`Got a response to an m-search: 
    ${code},
    ${JSON.stringify(headers)},
    ${JSON.stringify(rinfo)}.`);*/
  if(headers.USN === 'uuid:93691f7e-9962-1e85-ffff-ffffc4ed5d70::urn:schemas-upnp-org:service:EarthService:1') {
    address = rinfo.address;
    port = rinfo.port;
  } else {
    console.log('headers.USN:');
    console.log(headers.USN);
    console.log('JSON.parse(headers).USN:');
    console.log(JSON.parse(headers).USN);
  }
})

// client.search('urn:schemas-upnp-org:service:ContentDirectory:1');
client.search('ssdp:all');

setTimeout(() => {
  console.log(`GOT ${nbResponses} RESPONSE(S)`);
  client.stop()
}, 5000)