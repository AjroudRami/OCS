package com.subutai.nova.web.ws;

import com.subutai.nova.arduino.ArduinoCommander;
import com.subutai.nova.arduino.CommandCallback;
import com.subutai.nova.arduino.FailureResponse;
import com.subutai.nova.arduino.Orientation;
import com.subutai.nova.arduino.command.CommandResponse;
import com.subutai.nova.arduino.command.list.RequestYPR;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class OrientationImpl implements OrientationWS {

    @EJB
    ArduinoCommander commander;

    private static Logger LOGGER = Logger.getLogger(OrientationImpl.class.getSimpleName());
    private boolean response = false;
    private long startTime = 0;
    private long TIMEOUT = 1000;
    private Orientation orientation;
    private CallbackHandler handler;

    @Override
    public Response queryOrientation() {
        orientation = null;
        handler = new CallbackHandler();
        startTime = System.currentTimeMillis();
        response = false;
        RequestYPR request = new RequestYPR();
        request.setCommandCallback(handler);
        commander.sendCommand(request);
        LOGGER.log(Level.INFO, "Waiting");
        while (!response){
            if(System.currentTimeMillis() - startTime > TIMEOUT) {
                break;
            }
        }
        LOGGER.log(Level.INFO, "Done waiting");

        if (orientation != null) {
            return Response.ok().entity(orientation).build();
        }
        return Response.status(500).entity("{ \"msg\": \"Sensor did not respond :" + response
                + "\"}").build();
    }

    private class CallbackHandler implements CommandCallback {

        CallbackHandler() {
        }

        @Override
        public void onSuccess(CommandResponse resp) {
            LOGGER.log(Level.INFO, "Successfully received response");
            orientation = Orientation.fromBytes(resp.getBytes());
            response = true;
        }

        @Override
        public void onFailure(FailureResponse resp) {
            LOGGER.log(Level.INFO, "FAILURE: " + resp.getMessage());
            response = true;
        }
    }

}
