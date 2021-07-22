package com.example.demo.mapper;

import com.example.demo.dto.SendgridMailTemplateDTO;
import com.example.demo.dto.TemplatesRequest.MailTemplateDTO;
import com.example.demo.model.SendgridMailTemplate;

public interface SeparateTemplateMapper {

    SendgridMailTemplate mailTemplateDtoToMailTemplate(MailTemplateDTO MailTemplateDTO);
}
