package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:values.properties")
public class SendgridConfiguration {

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${sendgrid.path.templates}")
    private String templatesPath;

    @Value("${sendgrid.templates.generationsKey}")
    private String generationsKey;

    @Value("${sendgrid.templates.generationsValue}")
    private String generationsValue;

    @Value("${sendgrid.pageTokenKey}")
    private String pageTokenKey;

    @Value("${sendgrid.templates.pageSizeKey}")
    private String pageSizeKey;

    @Value("${sendgrid.templates.pageSizeValue}")
    private String pageSizeValue;

    @Value("${sendgrid.path.sendmail}")
    private String sendMailPath;


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTemplatesPath() {
        return templatesPath;
    }

    public void setTemplatesPath(String templatesPath) {
        this.templatesPath = templatesPath;
    }

    public String getGenerationsKey() {
        return generationsKey;
    }

    public void setGenerationsKey(String generationsKey) {
        this.generationsKey = generationsKey;
    }

    public String getGenerationsValue() {
        return generationsValue;
    }

    public void setGenerationsValue(String generationsValue) {
        this.generationsValue = generationsValue;
    }

    public String getPageTokenKey() {
        return pageTokenKey;
    }

    public void setPageTokenKey(String pageTokenKey) {
        this.pageTokenKey = pageTokenKey;
    }

    public String getPageSizeKey() {
        return pageSizeKey;
    }

    public void setPageSizeKey(String pageSizeKey) {
        this.pageSizeKey = pageSizeKey;
    }

    public String getPageSizeValue() {
        return pageSizeValue;
    }

    public void setPageSizeValue(String pageSizeValue) {
        this.pageSizeValue = pageSizeValue;
    }

    public String getSendMailPath() {
        return sendMailPath;
    }

    public void setSendMailPath(String sendMailPath) {
        this.sendMailPath = sendMailPath;
    }
}
