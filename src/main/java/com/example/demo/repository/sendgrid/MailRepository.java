package com.example.demo.repository.sendgrid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository {
    void sendMail(Mail mail);
}
