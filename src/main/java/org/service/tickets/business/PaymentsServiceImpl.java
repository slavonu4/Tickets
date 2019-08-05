package org.service.tickets.business;

import org.service.tickets.domain.model.TicketStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentsServiceImpl.class);

    private final Random random = new Random();

    @Override
    public TicketStatus processTicket(Long ticketId) {
        LOGGER.info("Received request to process a payment for the ticket #{}", ticketId);

        TicketStatus status = getRandomStatus();
        LOGGER.info("A payment for the ticket #{} has been processed. New status {}", ticketId, status);

        return status;
    }

    private TicketStatus getRandomStatus() {
        int statusesAmount = TicketStatus.values().length;
        int statusIndex = random.nextInt(statusesAmount);

        return TicketStatus.values()[statusIndex];
    }
}
