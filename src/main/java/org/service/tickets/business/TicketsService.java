package org.service.tickets.business;

import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.presentation.dto.NewTicketDTO;

import java.util.Optional;

public interface TicketsService {
    Long createTicket(NewTicketDTO newTicketInfo);

    Optional<TicketStatus> getStatusOf(Long ticketId);
}
