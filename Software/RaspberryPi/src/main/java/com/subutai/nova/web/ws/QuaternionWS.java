package com.subutai.nova.web.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/quaternions")
public interface QuaternionWS {

    @GET
    Response queryQuaternions();
}
