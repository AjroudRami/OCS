package com.subutai.nova.arduino;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

@Stateless
public class LedOnImpl implements LedOn {

    @EJB
    ArduinoCommander commander;

    public Response computePath() {
        commander.sendCommand(new com.subutai.nova.arduino.command.list.LedOn(java.awt.Color.RED.getRGB()));
        return Response.ok().build();
    }
}
