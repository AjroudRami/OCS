package com.subutai.nova.web.ws;

import com.subutai.nova.arduino.ArduinoCommander;
import com.subutai.nova.arduino.command.list.LedOn;
import com.subutai.nova.web.entities.LedRequest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.logging.Logger;

@Stateless
public class LedImpl implements LedWS {

    private static Logger LOGGER = Logger.getLogger(LedImpl.class.getName());

    @EJB
    ArduinoCommander commander;

    public Response ledOn(LedRequest request) {

        if (!checkRequest(request)) {
            return Response.status(400).entity("{\"message\": \"red, green, blue must be between 0 and 255 included\"}").build();
        }
        int color = new Color(request.getRed(), request.getGreen(), request.getBlue()).getRGB();
        commander.sendCommand(new LedOn(color));
        return Response.ok().build();
    }


    private boolean checkRequest(LedRequest request) {
        int red = request.getRed();
        int blue = request.getBlue();
        int green = request.getGreen();
        return red <= 255 && red >= 0 && blue <= 255 && blue >= 0 && green <= 255 && green >= 0;
    }
}
