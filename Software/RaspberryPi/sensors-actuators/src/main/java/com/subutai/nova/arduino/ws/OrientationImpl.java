package com.subutai.nova.arduino.ws;

import com.subutai.nova.arduino.*;
import com.subutai.nova.arduino.command.list.RequestYPR;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

@Stateless
public class OrientationImpl implements OrientationWS, CommandCallback {

    @EJB
    ArduinoCommander commander;

    private boolean response = false;
    private long startTime = 0;
    private long TIMEOUT = 500;
    private Orientation orientation;

    @Override
    public Response queryOrientation() {
        orientation = null;
        RequestYPR command = new RequestYPR();
        startTime = System.currentTimeMillis();
        response = false;
        Orientation orientation = null;
        RequestYPR request = new RequestYPR();
        request.setCommandCallback(this);
        commander.sendCommand(request);
        while (!response || (System.currentTimeMillis() - startTime < TIMEOUT)) ;

        if (orientation != null) {
            return Response.ok().entity(orientation).build();
        }
        return Response.status(500).entity("Sensor did not respond").build();
    }

    @Override
    public void onSuccess(CommandResponse response) {
        orientation = Orientation.fromBytes(response.getBytes());
        this.response = true;
    }

    @Override
    public void onFailure(FailureResponse response) {
        this.response = true;
    }
}
