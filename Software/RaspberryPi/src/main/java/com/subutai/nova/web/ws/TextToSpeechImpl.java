package com.subutai.nova.web.ws;

import com.subutai.nova.web.entities.ReadRequest;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Stateless
public class TextToSpeechImpl implements TextToSpeechWS {
    @Override
    public Response readText(ReadRequest request) {
        String text = request.getText();
        String lang = request.getLang();
        try {
            new ProcessBuilder("touch DIRECTORY.hihi").start();
            new ProcessBuilder("../../src/main/resources/textToSpeech.sh", text).start();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(500).entity("{\"message\" : " + e.getMessage() +"}").build();
        }
        return Response.ok().build();
    }
}
