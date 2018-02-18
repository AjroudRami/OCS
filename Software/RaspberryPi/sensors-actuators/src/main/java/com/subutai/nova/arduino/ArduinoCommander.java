package com.subutai.nova.arduino;

import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import com.subutai.nova.arduino.command.ArduinoCallbackCommand;
import com.subutai.nova.arduino.command.ArduinoCommand;
import com.subutai.nova.arduino.command.CommandResponse;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@LocalBean
@Startup
@Singleton
@DependsOn("ArduinoBoard")
public class ArduinoCommander implements SerialDataEventListener {

    private static Logger LOGGER = Logger.getLogger(ArduinoBoard.class.getName());
    private static CallbackRegistry registry;
    @EJB
    ArduinoBoard board;

    @PostConstruct
    private void init() {
        registry = new CallbackRegistry();
        board.registerDataListener(this);
    }

    public boolean sendCommand(ArduinoCommand command) {
        LOGGER.log(Level.INFO, "Sending command " + command.getId());
        try {
            LOGGER.log(Level.INFO, "parsed command: " + Arrays.toString(command.parseCommand()));
            board.write(command.parseCommand());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, "IOException raised while sending command: " + command.getId());
            return false;
        } catch (CommandSizeExceedException c) {
            c.printStackTrace();
            LOGGER.log(Level.WARNING, "Command size exceed the maximum size, not sent. id = " + command.getId());
        }
        return true;
    }

    /**
     * send a Command to the ArduinoBoard
     * if an error is thrown while sending, the onFailure method of the CommandCallback will be triggered with
     * an IOEXception FailureResponse
     * if no response is received from the arduino within the time window,
     * a timeout FailureResponse will be sent to the onFailure method;
     *
     * @param command
     * @return
     */
    public boolean sendCommand(ArduinoCallbackCommand command) {
        LOGGER.log(Level.INFO, "Sending command " + command.getId());
        try {
            registry.registerCallbackCommand(command);
            LOGGER.log(Level.INFO, "Sending: " + Arrays.toString(command.parseCommand()));
            board.write(command.parseCommand());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warning(e.getMessage());
            command.onFailure(FailureResponse.IOException);
            registry.removeCallback(command);
            return false;
        } catch (CallbackRegistryException e) {
            e.printStackTrace();
            LOGGER.warning(e.getMessage());
            command.onFailure(FailureResponse.RegistryError);
            registry.removeCallback(command);
            return false;
        } catch (CommandSizeExceedException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, "Command size exceed the maximum size, not sent. id = " + command.getId());
        }
        return true;
    }

    @Override
    public void dataReceived(SerialDataEvent serialDataEvent) {
        try {
            byte[] rawResponse = serialDataEvent.getBytes();
            LOGGER.info("received: " + Arrays.toString(rawResponse));
            CommandResponse response = CommandResponse.fromBytes(rawResponse);
            registry.notifyResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
