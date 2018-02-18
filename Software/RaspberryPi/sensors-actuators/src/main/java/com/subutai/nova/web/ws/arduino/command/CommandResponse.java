package com.subutai.nova.web.ws.arduino.command;

import com.subutai.nova.web.ws.arduino.BinaryConverter;
import com.subutai.nova.web.ws.arduino.ByteArrayUtil;

public class CommandResponse {

    private byte id;
    private short callbackId;
    private byte[] bytes;
    private byte length;

    public static CommandResponse fromBytes(byte[] bytes) {
        CommandResponse response = new CommandResponse();
        response.id = bytes[0];
        response.length = bytes[1];
        response.callbackId = BinaryConverter.getShort(new byte[]{bytes[2], bytes[3]});
        byte[] payload = new byte[bytes.length - 4];
        response.bytes = payload;
        ByteArrayUtil.copy(bytes, response.bytes, 4, 0, response.bytes.length);
        return response;
    }

    public byte getId() {
        return id;
    }

    public short getCallbackId() {
        return this.callbackId;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

}
