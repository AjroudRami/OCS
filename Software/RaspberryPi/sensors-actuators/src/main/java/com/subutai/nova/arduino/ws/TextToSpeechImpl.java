package com.subutai.nova.arduino.ws;

import com.subutai.nova.arduino.ws.entities.ReadRequest;

import javax.ws.rs.core.Response;

public class TextToSpeechImpl implements TextToSpeechWS {
    @Override
    public Response readText(ReadRequest request) {
        String text = request.getText();
        String lang = request.getLang();
        /*try {
            new ProcessBuilder("src/main/resources/textToSpeech.sh", text).start();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }*/
        return Response.ok().build();
    }
}
