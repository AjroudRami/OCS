package com.subutai.nova.web.ws.arduino;

import com.subutai.nova.web.ws.arduino.command.ArduinoCallbackCommand;
import org.junit.Test;

public class CallbackRegistryTest {

    @Test
    public void testGetId() throws CallbackRegistryException {
        CallbackRegistry registry = new CallbackRegistry();
        registry.registerCallbackCommand(new ArduinoCallbackCommand() {
            @Override
            public byte[] getDescription() {
                return new byte[0];
            }
        });
    }
}
