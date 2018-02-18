package com.subutai.nova.web.ws.arduino.command.list;

import com.subutai.nova.web.ws.arduino.command.ArduinoCallbackCommand;
import com.subutai.nova.web.ws.arduino.command.Commands;

public class RequestBatteryState extends ArduinoCallbackCommand {

    public RequestBatteryState() {
        super.id = Commands.REQ_BAT_STATE;
    }

    @Override
    public byte[] getDescription() {
        return new byte[0];
    }
}
