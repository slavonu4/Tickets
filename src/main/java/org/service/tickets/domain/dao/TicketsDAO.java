package org.service.tickets.domain.dao;

import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;

import java.util.Optional;
import java.util.Set;

public interface TicketsDAO {
    Ticket save(Ticket ticket);

    Optional<TicketStatus> findStatusById(Long ticketId);

    Optional<Long> findTicketIdByStatusNotIn(Set<TicketStatus> statuses);

    void changeStatusFor(Long ticketId, TicketStatus newStatus);
}
