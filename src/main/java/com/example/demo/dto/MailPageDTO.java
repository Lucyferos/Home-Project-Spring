package com.example.demo.dto;

import com.example.demo.dto.TemplatesRequest.MailTemplateDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MailPageDTO {

    @JsonProperty("result")
    private List<MailTemplateDTO> mailTemplateDTOs;

    @JsonProperty("_metadata")
    private MetadataDTO metadataDTO;

    public List<MailTemplateDTO> getMailTemplateDTOs() {
        return mailTemplateDTOs;
    }

    public void setMailTemplateDTOs(List<MailTemplateDTO> mailTemplateDTOs) {
        this.mailTemplateDTOs = mailTemplateDTOs;
    }

    public MetadataDTO getMetadataDTO() {
        return metadataDTO;
    }

    public void setMetadataDTO(MetadataDTO metadataDTO) {
        this.metadataDTO = metadataDTO;
    }
}