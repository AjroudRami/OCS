package com.subutai.nova.arduino;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.logging.Logger;

@Startup
@Singleton(name = "BluetoothSerialBinder")
public class BluetoothSerialBinder {
    private static Logger LOGGER = Logger.getLogger(BluetoothSerialBinder.class.getName());
    private boolean devellop = true;

    @PostConstruct
    public void makeSerial() {
        if (devellop) {
            try {
                new ProcessBuilder("src/main/resources/serial.sh", "").start();
            } catch (IOException e) {
                LOGGER.severe("Error while creating serial connextion: " + e.getMessage());
            }
        } else {
            LOGGER.info("devellop is false, Skipping bluetooth serial initialisation");
        }
    }
}
