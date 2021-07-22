package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "mail_template")
public class SendgridMailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sendgrid_id")
    private String sendgridId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
