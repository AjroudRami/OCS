package com.subutai.nova.arduino.command.list;

import com.subutai.nova.arduino.command.ArduinoCallbackCommand;
import com.subutai.nova.arduino.command.Commands;

public class RequestYPR extends ArduinoCallbackCommand {

    public RequestYPR() {
        super.id = (byte) Commands.REQ_YPR;
    }

    @Override
    public byte[] getDescription() {
        return new byte[0];
    }
}
