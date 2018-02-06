package com.subutai.nova.arduino;

import com.subutai.nova.arduino.command.ArduinoCallbackCommand;
import com.subutai.nova.arduino.command.CommandResponse;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CallbackRegistry {

    private final int COMMAND_STORAGE_SIZE = 1024;
    private final long SLOT_TIMEOUT = 5000;
    private ArduinoCallbackCommand[] commands;

    public CallbackRegistry(){
        this.commands = new ArduinoCallbackCommand[COMMAND_STORAGE_SIZE];
    }

    public synchronized void registerCallbackCommand(ArduinoCallbackCommand command) throws CallbackRegistryException {
        short slotID = getFreeSlot();
        if (slotID < 0) {
            throw new CallbackRegistryException();
        }
        command.setCallbackId(slotID);
        System.out.println("Register ID : " + slotID);
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
        this.commands[command.getCallbackId()] = null;
    }

    public synchronized short getFreeSlot() {
        for (short i = 0; i < commands.length; i++) {
            if(commands[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private synchronized void makeTimeout(int slotId){
        //TODO Executor service should be shutdown if the command fails to be sent.
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(() -> {
            slotTimeout(slotId);
            scheduledExecutorService.shutdownNow();
        }, SLOT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private synchronized void slotTimeout(int slotId){
        if (this.commands[slotId] != null) {
            ArduinoCallbackCommand cmd = commands[slotId];
            cmd.onFailure(FailureResponse.Timeout);
            this.commands[slotId] = null;
        }
    }
}
