package com.example.demo.service.sendgrid;

import com.example.demo.repository.sendgrid.MailSenderRepository;

import java.util.Collection;
import java.util.List;

public class MailServiceImpl {
    private MailSenderRepository mailSenderRepository;
    private MailRepository mailRepository;

    public MailServiceImpl(MailSenderRepository mailSenderRepository, MailRepository mailRepository) {
        this.mailSenderRepository = mailSenderRepository;
        this.mailRepository = mailRepository;
    }

    @Override
    public void sendEmailNotification(Order order, MailType mailType) {
        if(order.getMailTemplates().get(mailType)!=null) {
            Mail mailToSend = prepareMail(order, mailType);
            mailRepository.sendMail(mailToSend);
        }
    }

    @Override
    public void sendEmailNotificationSingleParcel(SingleParcel singleParcel, MailType mailType) {
        if(singleParcel.getMailTemplates().get(mailType) != null) {
            Mail mailToSend = prepareMailSingleParcel(singleParcel, mailType);
            mailRepository.sendMail(mailToSend);
        }
    }

    @Override
    public Mail prepareMailSingleParcel(SingleParcel singleParcel, MailType mailType) {
        Mail mail = new Mail();
        mail = addOrderToMailSingleParcel(mail, singleParcel);
        MailSender mailSender = new MailSender();
        mailSender.setName("Maciejowski");
        mailSender.setEmail("blawat.2003@onet.pl");
        mail.setSender(mailSender);
        //  mail = addSenderToMail(mail);
        mail = addMailRecipientToMailSingleParcel(mail, singleParcel);
        mail = addMailTemplateToMailSingleParcel(mail, singleParcel , mailType);
        return mail;
    }

    private Mail addOrderToMailSingleParcel(Mail mail, SingleParcel singleParcel) {
        mail.setSingleParcel(singleParcel);
        return mail;
    }

    private Mail addMailRecipientToMailSingleParcel(Mail mail, SingleParcel singleParcel) {
        MailRecipient mailRecipient = new MailRecipient(singleParcel.getEmail(), singleParcel.getRecipientName());
        List<MailRecipient> mailRecipientList = List.of(mailRecipient);
        mail.setMailRecipients(mailRecipientList);
        return mail;
    }

    private Mail addMailTemplateToMailSingleParcel(Mail mail, SingleParcel singleParcel, MailType mailType) {
        Collection<MailTemplate> mailTemplates = singleParcel.getMailTemplates().values();
        MailTemplate mailTemplate = mailTemplates.stream().filter(template -> template.getMailType()==mailType).findFirst().get();
        mail.setMailTemplate(mailTemplate);
        return mail;
    }



    private Mail prepareMail(Order order, MailType mailType) {
        Mail mail = new Mail();
        mail = addOrderToMail(mail, order);
        mail = addSenderToMail(mail);
        mail = addMailRecipientToMail(mail, order.getRecipient());
        mail = addMailTemplateToMail(mail, order, mailType);
        return mail;
    }

    private Mail addOrderToMail(Mail mail, Order order) {
        mail.setOrder(order);
        return mail;
    }

    private Mail addSenderToMail(Mail mail) {
        MailSender sender = mailSenderRepository.findById(MailSender.ID).get();
        mail.setSender(sender);
        return mail;
    }

    private Mail addMailRecipientToMail(Mail mail, Recipient recipient) {
        MailRecipient mailRecipient = new MailRecipient(recipient.getEmail(), recipient.getName());
        List<MailRecipient> mailRecipientList = List.of(mailRecipient);
        mail.setMailRecipients(mailRecipientList);
        return mail;
    }

    private Mail addMailTemplateToMail(Mail mail, Order order, MailType mailType) {
        Collection<MailTemplate> mailTemplates = order.getMailTemplates().values();
        MailTemplate mailTemplate = mailTemplates.stream().filter(template -> template.getMailType()==mailType).findFirst().get();
        mail.setMailTemplate(mailTemplate);
        return mail;
    }
}
