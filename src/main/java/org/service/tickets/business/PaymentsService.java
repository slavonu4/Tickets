package org.service.tickets.business;

import org.service.tickets.domain.model.TicketStatus;

public interface PaymentsService {
    TicketStatus processTicket(Long ticketId);
}
