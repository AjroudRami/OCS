package com.subutai.nova.arduino.ws;

import com.subutai.nova.arduino.ws.entities.ReadRequest;

import javax.ws.rs.core.Response;

public class ReadTextImpl implements ReadText {
    @Override
    public Response readText(ReadRequest request) {
        return Response.ok().build();
    }
}
