package org.service.tickets.business;

import org.service.tickets.domain.dao.TicketsDAO;
import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
public class TicketsProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketsProcessor.class);
    private static final String BASE_PAYMENT_PROCESS_URL = "http://localhost:8080/payments/process/";

    private final TicketsDAO dao;
    private final RestTemplate restTemplate;
    private final Set<TicketStatus> finalStatuses;

    public TicketsProcessor(TicketsDAO dao) {
        this.dao = dao;
        finalStatuses = EnumSet.of(TicketStatus.ERROR, TicketStatus.PROCESSED);
        restTemplate = new RestTemplate();
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void processTicket() {
        var ticketIdToProcessOpt = dao.findTicketIdByStatusNotIn(finalStatuses);
        if (ticketIdToProcessOpt.isEmpty()){
            LOGGER.info("There are no more tickets for processing");
            return;
        }

        var ticketIdToProcess = ticketIdToProcessOpt.get();
        LOGGER.info("The ticket #{} was selected to be processed", ticketIdToProcess);

        processTicket(ticketIdToProcess).ifPresent(newStatus -> dao.changeStatusFor(ticketIdToProcess, newStatus));
    }

    private Optional<TicketStatus> processTicket(Long ticketIdToProcess) {
        var url = BASE_PAYMENT_PROCESS_URL + ticketIdToProcess;
        var response = restTemplate.postForEntity(url, null, TicketStatus.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            TicketStatus result = response.getBody();
            LOGGER.info("The ticket #{} was processed. New status: {}", ticketIdToProcess, result);
            return Optional.ofNullable(result);
        } else {
            LOGGER.info("Payment service returned error code {} while processing the ticket #{}", response.getStatusCodeValue(), ticketIdToProcess);
            return Optional.empty();
        }
    }
}
