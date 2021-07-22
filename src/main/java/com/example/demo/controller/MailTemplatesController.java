package com.example.demo.controller;

import com.example.demo.dto.SendgridMailTemplateDTO;
import com.example.demo.mapper.SendgridMailTemplateMapper;
import com.example.demo.model.SendgridMailTemplate;
import com.example.demo.repository.SendgridMailTemplateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/templates")
public class MailTemplatesController {
    private final SendgridMailTemplateMapper sendgridMailTemplateMapper;
    private final SendgridMailTemplateRepository sendgridMailTemplateRepository;

    public MailTemplatesController(SendgridMailTemplateMapper sendgridMailTemplateMapper, SendgridMailTemplateRepository sendgridMailTemplateRepository) {
        this.sendgridMailTemplateMapper = sendgridMailTemplateMapper;
        this.sendgridMailTemplateRepository = sendgridMailTemplateRepository;
    }

    @GetMapping()
    public ResponseEntity<List<SendgridMailTemplateDTO>> getMailTemplates() {
        List<SendgridMailTemplate> mailTemplates = sendgridMailTemplateRepository.findAll();
        List<SendgridMailTemplateDTO> mailTemplateDTOs = mailTemplates.stream().map(sendgridMailTemplateMapper::mailTemplateToMailTemplateDto).collect(Collectors.toList());
        return ResponseEntity.ok(mailTemplateDTOs);
    }
}
