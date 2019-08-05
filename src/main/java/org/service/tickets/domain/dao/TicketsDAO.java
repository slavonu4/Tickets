package org.service.tickets.domain.dao;

import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;

import java.util.Optional;

public interface TicketsDAO {
    Ticket save(Ticket ticket);

    Optional<TicketStatus> findStatusById(Long ticketId);
}
