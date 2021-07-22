package com.example.demo.repository;

import com.example.demo.configuration.SendgridConfiguration;
import com.example.demo.model.Mail;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class MailRepositoryImpl implements MailRepository{
    private final SendgridConfiguration sendgridConfiguration;
    private Logger logger = LoggerFactory.getLogger(MailRepository.class);

    public MailRepositoryImpl(SendgridConfiguration sendgridConfiguration) {
        this.sendgridConfiguration = sendgridConfiguration;
    }

    @Override
    public void sendMail(Mail mail) {
        try {
            int statusCode = sendRequestWithMail(mail);
            processStatusCode(statusCode);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error occurred", e);
        }
    }


    private int sendRequestWithMail(Mail mail) throws IOException {
        SendGrid sg = new SendGrid(sendgridConfiguration.getApiKey());
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint(sendgridConfiguration.getSendMailPath());
        String mailBody = new ObjectMapper().writeValueAsString(mail);
        request.setBody(mailBody);
        Response response = sg.api(request);
        return response.getStatusCode();
    }

    private void processStatusCode(int statusCode) {
        if(statusCode>=400) {
            logger.error("STATUS CODE: " + statusCode);
        }
    }
}
