package com.subutai.nova.arduino.command.list;

import com.subutai.nova.arduino.command.ArduinoCommand;
import com.subutai.nova.arduino.command.Commands;

import java.nio.ByteBuffer;

public class StreamYPR extends ArduinoCommand {

    private boolean stream;

    public StreamYPR(boolean stream) {
        super.id = (byte) Commands.STREAM_YPR;
        this.stream = stream;

    }

    @Override
    public byte[] getDescription() {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.putInt(stream ? 1 : 0);
        return buffer.array();
    }
}
