package com.example.demo.repository.sendgrid;

import com.example.demo.configuration.sendgrid.SendgridConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class MailRepositoryImpl implements MailRepository {
    private SendgridConfiguration sendgridConfiguration;
    private SendgridMailMapper mailMapper;

    @Override
    public void sendMail(Mail mail) {
        MailDTO mailDTO = mailMapper.mailToMailDTO(mail);
        try {
            int statusCode = sendRequestWithMail(mailDTO);
            processStatusCode(statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private int sendRequestWithMail(MailDTO mailDTO) throws IOException {
            SendGrid sg = new SendGrid(sendgridConfiguration.getApiKey());
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint(sendgridConfiguration.getSendMailPath());
            String mailBody = new ObjectMapper().writeValueAsString(mailDTO);
            request.setBody(mailBody);
            Response response = sg.api(request);
            return response.getStatusCode();
        }

        private void processStatusCode(int statusCode) {
            if(statusCode>=400) {
                logger.error("STATUS CODE: " + String.valueOf(statusCode));
            }
        }
}
