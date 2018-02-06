package com.subutai.nova.arduino.ws;

import com.subutai.nova.arduino.ArduinoCommander;
import com.subutai.nova.arduino.CommandCallback;
import com.subutai.nova.arduino.FailureResponse;
import com.subutai.nova.arduino.Orientation;
import com.subutai.nova.arduino.command.CommandResponse;
import com.subutai.nova.arduino.command.list.RequestYPR;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

@Stateless
public class OrientationImpl implements OrientationWS {

    @EJB
    ArduinoCommander commander;

    private boolean response = false;
    private long startTime = 0;
    private long TIMEOUT = 500;
    private Orientation orientation;
    private CallbackHandler handler;

    @Override
    public Response queryOrientation() {
        orientation = null;
        handler = new CallbackHandler();
        RequestYPR command = new RequestYPR();
        startTime = System.currentTimeMillis();
        response = false;
        Orientation orientation = null;
        RequestYPR request = new RequestYPR();
        request.setCommandCallback(handler);
        commander.sendCommand(request);
        while (!response || (System.currentTimeMillis() - startTime > TIMEOUT)) ;

        if (orientation != null) {
            return Response.ok().entity(orientation).build();
        }
        return Response.status(500).entity("Sensor did not respond").build();
    }


    private class CallbackHandler implements CommandCallback {

        CallbackHandler() {
        }

        @Override
        public void onSuccess(CommandResponse resp) {
            orientation = Orientation.fromBytes(resp.getBytes());
            response = true;
        }

        @Override
        public void onFailure(FailureResponse resp) {
            response = true;
        }
    }

}
