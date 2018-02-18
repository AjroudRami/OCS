package com.subutai.nova.web.ws.arduino.command.list;

import com.subutai.nova.web.ws.arduino.command.ArduinoCallbackCommand;
import com.subutai.nova.web.ws.arduino.command.Commands;

public class RequestYPR extends ArduinoCallbackCommand {

    public RequestYPR() {
        super.id = (byte) Commands.REQ_YPR;
    }

    @Override
    public byte[] getDescription() {
        return new byte[0];
    }
}
