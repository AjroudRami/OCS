package com.subutai.nova.arduino.command.list;

import com.subutai.nova.arduino.command.ArduinoCommand;
import com.subutai.nova.arduino.command.Commands;

public class Buzz extends ArduinoCommand {

    public Buzz() {
        super.id = Commands.BUZZ;
    }

    @Override
    public byte[] getDescription() {
        return new byte[0];
    }
}
