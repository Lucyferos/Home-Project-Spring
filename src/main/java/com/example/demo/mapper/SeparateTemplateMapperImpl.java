package com.example.demo.mapper;

import com.example.demo.dto.SendgridMailTemplateDTO;
import com.example.demo.dto.TemplatesRequest.MailTemplateDTO;
import com.example.demo.model.SendgridMailTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeparateTemplateMapperImpl implements SeparateTemplateMapper{
    @Override
    public SendgridMailTemplate mailTemplateDtoToMailTemplate(MailTemplateDTO mailTemplateDTO) {
        SendgridMailTemplate sendgridMailTemplate = new SendgridMailTemplate();
        sendgridMailTemplate.setName(mailTemplateDTO.getName());
        sendgridMailTemplate.setSendgridId(mailTemplateDTO.getId());
        return sendgridMailTemplate;
    }
}
