package com.subutai.nova.arduino.ws;

import com.subutai.nova.arduino.ArduinoCommander;
import com.subutai.nova.arduino.CommandCallback;
import com.subutai.nova.arduino.FailureResponse;
import com.subutai.nova.arduino.command.CommandResponse;
import com.subutai.nova.arduino.command.list.RequestBatteryState;
import com.subutai.nova.arduino.ws.entities.BatteryState;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

public class BatteryStateBean implements BatteryStateWS, CommandCallback {

    @EJB
    ArduinoCommander commander;
    private boolean response = false;
    private long startTime = 0;
    private long TIMEOUT = 500;
    private BatteryState state;


    @Override
    public Response getBatteryState() {
        state = null;
        RequestBatteryState request = new RequestBatteryState();
        startTime = System.currentTimeMillis();
        response = false;
        request.setCommandCallback(this);
        commander.sendCommand(request);
        while (!response || (System.currentTimeMillis() - startTime < TIMEOUT)) ;

        if (state != null) {
            return Response.ok().entity(state).build();
        }
        return Response.status(500).entity("Sensor did not respond").build();
    }

    @Override
    public void onSuccess(CommandResponse response) {
        state = BatteryState.fromBytes(response.getBytes());
        this.response = true;
    }

    @Override
    public void onFailure(FailureResponse response) {
        this.response = true;
    }
}
