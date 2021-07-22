package com.example.demo.mapper;

import com.example.demo.dto.SendgridMailTemplateDTO;
import com.example.demo.model.SendgridMailTemplate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SendgridMailTemplateMapper {

    SendgridMailTemplate mailTemplateDtoToMailTemplate(SendgridMailTemplateDTO sendgridMailTemplateDTO);

    SendgridMailTemplateDTO mailTemplateToMailTemplateDto(SendgridMailTemplate sendgridMailTemplate);

}
