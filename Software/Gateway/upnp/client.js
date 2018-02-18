const ssdp = require('node-ssdp').Client;
const client = new ssdp({});

let nbResponses = 0;

client.on('notify', function () {
  // console.log('Got a notification.')
})

client.on('response', function inResponse(headers, code, rinfo) {
  nbResponses += 1;
  console.log(`Got a response to an m-search: 
    ${code},
    ${JSON.stringify(headers)},
    ${JSON.stringify(rinfo)}.`);
})

// client.search('urn:schemas-upnp-org:service:ContentDirectory:1');
client.search('ssdp:all');

setTimeout(() => {
  console.log(`GOT ${nbResponses} RESPONSE(S)`);
  client.stop()
}, 5000)