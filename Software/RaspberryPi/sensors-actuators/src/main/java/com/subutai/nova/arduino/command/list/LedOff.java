package com.subutai.nova.arduino.command.list;

import com.subutai.nova.arduino.command.ArduinoCommand;
import com.subutai.nova.arduino.command.Commands;

public class LedOff extends ArduinoCommand {

    public LedOff() {
        super.id = (byte) Commands.LED_OFF;
    }

    @Override
    public byte[] getDescription() {
        return new byte[0];
    }
}
