package com.subutai.nova.arduino.command.list;

import com.subutai.nova.arduino.command.ArduinoCallbackCommand;
import com.subutai.nova.arduino.command.Commands;

public class RequestQuaternions extends ArduinoCallbackCommand {

    public RequestQuaternions(){super.id = (byte) Commands.REQ_QUATS;}

    @Override
    public byte[] getDescription() {
        return new byte[0];
    }
}
