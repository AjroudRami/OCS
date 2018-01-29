package com.subutai.nova.arduino.ws;

import com.subutai.nova.arduino.ws.entities.ReadRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/read")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ReadText {

    @POST
    Response readText(ReadRequest request);
}
