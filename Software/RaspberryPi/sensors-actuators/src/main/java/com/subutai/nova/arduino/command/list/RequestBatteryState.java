package com.subutai.nova.arduino.command.list;

import com.subutai.nova.arduino.command.ArduinoCallbackCommand;
import com.subutai.nova.arduino.command.Commands;

public class RequestBatteryState extends ArduinoCallbackCommand {

    public RequestBatteryState() {
        super.id = Commands.REQ_BAT_STATE;
    }

    @Override
    public byte[] getDescription() {
        return new byte[0];
    }
}
