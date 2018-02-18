package com.subutai.nova.arduino.command.list;

import com.subutai.nova.arduino.command.ArduinoCommand;
import com.subutai.nova.arduino.command.Commands;

import java.nio.ByteBuffer;

public class LedOn extends ArduinoCommand {

    private int color;

    public LedOn(int color) {
        super.id = (byte) Commands.LED_ON;
        this.color = color;
    }

    @Override
    public byte[] getDescription() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(color);
        return buffer.array();
    }
}
