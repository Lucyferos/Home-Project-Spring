package com.example.demo.repository.sendgrid;

import java.util.List;

public interface SendgridPersonalizationMapper {
    PersonalizationDTO mailRecipientAndOrderToPersonalizationDTO(List<MailRecipient> mailRecipients, Order order);
}
