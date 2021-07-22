package com.example.demo.repository;

import com.example.demo.configuration.SendgridConfiguration;
import com.example.demo.dto.MailPageDTO;
import com.example.demo.mapper.SendgridMailTemplateMapper;
import com.example.demo.mapper.SeparateTemplateMapper;
import com.example.demo.model.SendgridMailTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SendgridMailTemplateRepositoryImpl implements SendgridMailTemplateRepository{
    private final SendgridConfiguration sendgridConfiguration;
    private Logger logger = LoggerFactory.getLogger(SendgridMailTemplateRepository.class);
    private final SeparateTemplateMapper separateTemplateMapper;

    public SendgridMailTemplateRepositoryImpl(SendgridConfiguration sendgridConfiguration, SeparateTemplateMapper separateTemplateMapper) {
        this.sendgridConfiguration = sendgridConfiguration;
        this.separateTemplateMapper = separateTemplateMapper;
    }

    @Override
    public List<SendgridMailTemplate> findAll() {
        List<SendgridMailTemplate> mailTemplates = new ArrayList<>();
        try {
            mailTemplates = findPage(null);
        } catch (IOException e) {
            logger.error("Exception occurred", e);
            e.printStackTrace();
        }
        return mailTemplates;
    }

    private List<SendgridMailTemplate> findPage(String pageToken) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = sendRequest(pageToken);
        MailPageDTO mailPageDTO = objectMapper.readValue(responseBody, MailPageDTO.class);
        List<SendgridMailTemplate> mailTemplates = mailPageDTO.getMailTemplateDTOs().stream().map(separateTemplateMapper::mailTemplateDtoToMailTemplate).collect(Collectors.toList());
        if (mailPageDTO.getMetadataDTO().getNextPage() != null) {
            String nextPageToken = extractPageToken(mailPageDTO.getMetadataDTO().getNextPage());
            mailTemplates.addAll(findPage(nextPageToken));
        }
        return mailTemplates;
    }
    private String sendRequest(String pageToken) throws IOException {
        SendGrid sg = new SendGrid(sendgridConfiguration.getApiKey());
        Request request = new Request();
        request.setMethod(Method.GET);
        request.setEndpoint(sendgridConfiguration.getTemplatesPath());
        request.addQueryParam(sendgridConfiguration.getGenerationsKey(), sendgridConfiguration.getGenerationsValue());
        request.addQueryParam(sendgridConfiguration.getPageSizeKey(), sendgridConfiguration.getPageSizeValue());
        if(pageToken!=null) {
            request.addQueryParam(sendgridConfiguration.getPageTokenKey(), pageToken);
        }
        Response response = sg.api(request);
        return response.getBody();
    }

    private String extractPageToken(String nextPageUrl) throws MalformedURLException {
        URL url = new URL(nextPageUrl);
        String query = url.getQuery();
        String[] splittedQuery = query.split("&");
        String nextPageToken = Arrays.stream(splittedQuery)
                .map(keyValuePair -> keyValuePair.split("="))
                .filter(splittedPair -> splittedPair[0].equals(sendgridConfiguration.getPageTokenKey()))
                .map(splittedPair -> splittedPair[1])
                .findFirst()
                .get();
        return nextPageToken;
    }
}
