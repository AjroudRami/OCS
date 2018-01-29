package com.subutai.nova.arduino.ws;

import com.subutai.nova.arduino.ArduinoCommander;
import com.subutai.nova.arduino.ws.entities.LedRequest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.logging.Logger;

@Stateless
public class LedOnImpl implements LedOn {

    private static Logger LOGGER = Logger.getLogger(LedOnImpl.class.getName());

    @EJB
    ArduinoCommander commander;

    public Response ledOn(LedRequest request) {

        if (!checkRequest(request)) {
            return Response.status(400).entity("red, green, blue must be between 0 and 255 included").build();
        }
        int color = new Color(request.getRed(), request.getGreen(), request.getBlue()).getRGB();
        commander.sendCommand(new com.subutai.nova.arduino.command.list.LedOn(color));
        return Response.ok().build();
    }


    private boolean checkRequest(LedRequest request) {
        int red = request.getRed();
        int blue = request.getBlue();
        int green = request.getGreen();
        return red <= 255 && red >= 0 && blue <= 255 && blue >= 0 && green <= 255 && green >= 0;
    }
}
