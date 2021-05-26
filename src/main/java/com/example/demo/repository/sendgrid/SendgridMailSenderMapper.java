package com.example.demo.repository.sendgrid;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SendgridMailSenderMapper {
    MailSenderDTO mailSenderToMailSenderDTO(MailSender mailSender);
}
