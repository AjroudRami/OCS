package com.subutai.nova.arduino;

import com.subutai.nova.arduino.command.ArduinoCallbackCommand;
import com.subutai.nova.arduino.command.CommandResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CallbackRegistry {

    private static final Logger LOGGER = Logger.getLogger(CallbackRegistry.class.getSimpleName());
    private final int COMMAND_STORAGE_SIZE = 1024;
    private final long SLOT_TIMEOUT = 5000;
    private ArduinoCallbackCommand[] commands;

    public CallbackRegistry() {
        this.commands = new ArduinoCallbackCommand[COMMAND_STORAGE_SIZE];
    }

    public synchronized void registerCallbackCommand(ArduinoCallbackCommand command) throws CallbackRegistryException {
        short slotID = getFreeSlot();
        if (slotID < 0) {
            throw new CallbackRegistryException();
        }
        command.setCallbackId(slotID);
        System.out.println("Register ID : " + slotID + " at " + System.currentTimeMillis());
        this.commands[slotID] = command;
        makeTimeout(slotID);
    }

    public synchronized void notifyResponse(CommandResponse response) {
        short id = response.getCallbackId();
        System.out.println("ID : " + id);
        ArduinoCallbackCommand command = this.commands[id];
        try {
            command.onSuccess(response);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        this.commands[id] = null;
    }

    public synchronized void removeCallback(ArduinoCallbackCommand command) {
        LOGGER.log(Level.INFO, "Remove callback id " + command.getCallbackId());
        this.commands[command.getCallbackId()] = null;
    }

    public synchronized short getFreeSlot() {
        for (short i = 0; i < commands.length; i++) {
            if (commands[i] == null) {
                LOGGER.log(Level.INFO, "Giving free slot id " + i);
                return i;
            }
        }
        return -1;
    }

    private synchronized void makeTimeout(int slotId) {
        //TODO Executor service should be shutdown if the command fails to be sent.
        LOGGER.log(Level.INFO, "TimeoutJob start for id " + slotId + " at " + System.currentTimeMillis() + "ms");
        Thread t = new Thread(new TimeoutJob(this.commands, this.SLOT_TIMEOUT, slotId));
        t.start();
    }

    private synchronized void slotTimeout(int slotId) {
        if (this.commands[slotId] != null) {
            ArduinoCallbackCommand cmd = commands[slotId];
            cmd.onFailure(FailureResponse.Timeout);
            this.commands[slotId] = null;
        }
    }

    private class TimeoutJob implements Runnable {

        private ArduinoCallbackCommand[] callbackCommands;
        private long timeout;
        private int id;

        public TimeoutJob(ArduinoCallbackCommand[] callbackCommand, long timeout, int id) {
            this.callbackCommands = callbackCommand;
            this.timeout = timeout;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.callbackCommands[id] = null;
        }
    }
}
