package com.subutai.nova.web.ws.arduino;

import com.subutai.nova.web.ws.arduino.command.CommandResponse;

public interface CommandCallback {

    void onSuccess(CommandResponse response);

    void onFailure(FailureResponse response);
}
