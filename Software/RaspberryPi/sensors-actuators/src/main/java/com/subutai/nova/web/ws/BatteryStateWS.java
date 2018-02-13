package com.subutai.nova.web.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/batteryState")
@Produces(MediaType.APPLICATION_JSON)
public interface BatteryStateWS {

    @GET
    Response getBatteryState();
}
