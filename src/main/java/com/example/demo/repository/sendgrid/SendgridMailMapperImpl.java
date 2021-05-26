package com.example.demo.repository.sendgrid;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendgridMailMapperImpl implements SendgridMailMapper{
    private SendgridMailSenderMapper mailSenderMapper;
    private SendgridPersonalizationMapper personalizationMapper;

    public SendgridMailMapperImpl(SendgridMailSenderMapper mailSenderMapper, SendgridPersonalizationMapper personalizationMapper) {
        this.mailSenderMapper = mailSenderMapper;
        this.personalizationMapper = personalizationMapper;
    }

    @Override
    public MailDTO mailToMailDTO(Mail mail) {
        MailDTO mailDTO = new MailDTO();
        if(mail==null) {
            return null;
        }
        mailDTO.setPersonalizationDTOs(List.of(personalizationMapper.mailRecipientAndOrderToPersonalizationDTO(mail.getMailRecipients(), mail.getOrder())));
        mailDTO.setReplyTo(mailSenderMapper.mailSenderToMailSenderDTO(mail.getSender()));
        mailDTO.setSenderInfo(mailSenderMapper.mailSenderToMailSenderDTO(mail.getSender()));
        mailDTO.setTemplateId(mail.getMailTemplate().getSendgridId());
        return mailDTO;
    }
}
