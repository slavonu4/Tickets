package org.service.tickets.domain.dao;

import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.domain.repository.TicketsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TicketsDAOImpl implements TicketsDAO {
    private final TicketsRepository repository;

    public TicketsDAOImpl(TicketsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public Optional<TicketStatus> findStatusById(Long ticketId) {
        return repository.findStatusById(ticketId);
    }
}
