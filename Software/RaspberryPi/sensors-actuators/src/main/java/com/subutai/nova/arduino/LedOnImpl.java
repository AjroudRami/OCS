package com.subutai.nova.arduino;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Stateless
public class LedOnImpl implements LedOn {

    private static Logger LOGGER = Logger.getLogger(LedOnImpl.class.getName());

    @EJB
    ArduinoCommander commander;

    public Response computePath() {
        LOGGER.info("commander = " + commander);
        commander.sendCommand(new com.subutai.nova.arduino.command.list.LedOn(java.awt.Color.RED.getRGB()));
        return Response.ok().build();
    }
}
