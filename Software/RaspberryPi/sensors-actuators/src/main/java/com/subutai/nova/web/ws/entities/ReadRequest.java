package com.subutai.nova.web.ws.entities;

public class ReadRequest {

    private String lang;
    private String text;

    public ReadRequest() {
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
