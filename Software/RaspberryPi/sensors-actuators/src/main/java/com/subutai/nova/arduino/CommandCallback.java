package com.subutai.nova.arduino;

import com.subutai.nova.arduino.command.CommandResponse;

public interface CommandCallback {

    void onSuccess(CommandResponse response);
    void onFailure(FailureResponse response);
}
