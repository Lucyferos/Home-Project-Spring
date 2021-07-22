package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendgridMailTemplateDTO {

    @JsonProperty("sendgrid_id")
    private String sendgridId;

    private String name;

    public String getSendgridId() {
        return sendgridId;
    }

    public void setSendgridId(String sendgridId) {
        this.sendgridId = sendgridId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
