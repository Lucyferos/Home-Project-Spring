package com.example.demo.repository.sendgrid;

import java.util.List;
import java.util.stream.Collectors;

public class SendgridPersonalizationMapperImpl implements SendgridPersonalizationMapper{
    private SendgridOrderMapper orderMapper;
    private SendgridMailRecipientMapper mailRecipientMapper;

    public SendgridPersonalizationMapperImpl(SendgridOrderMapper orderMapper, SendgridMailRecipientMapper mailRecipientMapper) {
        this.orderMapper = orderMapper;
        this.mailRecipientMapper = mailRecipientMapper;
    }

    public SendgridOrderMapper getOrderMapper() {
        return orderMapper;
    }

    public void setOrderMapper(SendgridOrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public SendgridMailRecipientMapper getMailRecipientMapper() {
        return mailRecipientMapper;
    }

    public void setMailRecipientMapper(SendgridMailRecipientMapper mailRecipientMapper) {
        this.mailRecipientMapper = mailRecipientMapper;
    }

    @Override
    public PersonalizationDTO mailRecipientAndOrderToPersonalizationDTO(List<MailRecipient> mailRecipients, Order order) {
        PersonalizationDTO personalizationDTO = new PersonalizationDTO();
        personalizationDTO.setTemplateData(orderMapper.orderToOrderDTO(order));
        if(mailRecipients!=null) {
            personalizationDTO.setMailRecipientDTOs(
                    mailRecipients.stream()
                            .map(mailRecipientMapper::mailRecipientToMailRecipientDTO)
                            .collect(Collectors.toList())
            );
        }

        return personalizationDTO;
    }
}
