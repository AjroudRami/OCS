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
            ProcessBuilder p1 = new ProcessBuilder("bash", "touch",  "DIRECTORY.hihi");
            ProcessBuilder p2 = new ProcessBuilder("bash", "../../src/main/resources/textToSpeech.sh", text);
            Process pro1 = p1.inheritIO().start();
            Process pro2 = p2.inheritIO().start();
            pro1.waitFor();
            pro2.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Response.status(500).entity("{\"message\" : " + e.getMessage() +"}").build();
        }
        return Response.ok().build();
    }
}
