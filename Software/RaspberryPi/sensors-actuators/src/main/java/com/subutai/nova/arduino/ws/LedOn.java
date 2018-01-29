package com.subutai.nova.arduino.ws;

import com.subutai.nova.arduino.ws.entities.LedRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ledOn")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LedOn {


    @POST
    Response ledOn(LedRequest request);
}