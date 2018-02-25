package com.subutai.nova.web.ws;

import com.subutai.nova.arduino.*;
import com.subutai.nova.arduino.command.CommandResponse;
import com.subutai.nova.arduino.command.list.RequestQuaternions;
import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuaternionImpl implements QuaternionWS {

    @EJB
    ArduinoCommander commander;

    private static Logger LOGGER = Logger.getLogger(QuaternionImpl.class.getSimpleName());
    private boolean response = false;
    private long startTime = 0;
    private long TIMEOUT = 1000;
    private Quaternions quaternions;
    private QuaternionImpl.CallbackHandler handler;

    @Override
    public Response queryQuaternions() {
        quaternions = null;
        handler = new QuaternionImpl.CallbackHandler();
        startTime = System.currentTimeMillis();
        response = false;
        RequestQuaternions request = new RequestQuaternions();
        request.setCommandCallback(handler);
        commander.sendCommand(request);
        LOGGER.log(Level.INFO, "Waiting");
        while (!response){
            if(System.currentTimeMillis() - startTime > TIMEOUT) {
                break;
            }
        }
        LOGGER.log(Level.INFO, "Done waiting");

        if (quaternions != null) {
            return Response.ok().entity(quaternions).build();
        }
        return Response.status(500).entity("{ \"message\": \"Sensor did not respond :" + response
                + "\"}").build();
    }

    private class CallbackHandler implements CommandCallback {

        CallbackHandler() {
        }

        @Override
        public void onSuccess(CommandResponse resp) {
            LOGGER.log(Level.INFO, "Successfully received response");
            quaternions = Quaternions.fromBytes(resp.getBytes());
            response = true;
        }

        @Override
        public void onFailure(FailureResponse resp) {
            LOGGER.log(Level.INFO, "FAILURE: " + resp.getMessage());
            response = true;
        }
    }
}
