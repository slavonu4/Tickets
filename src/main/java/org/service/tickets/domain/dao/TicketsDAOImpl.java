package org.service.tickets.domain.dao;

import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.domain.repository.TicketsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

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

    @Override
    public Optional<Long> findTicketIdByStatusNotIn(Set<TicketStatus> statuses) {
        var pageRequest = PageRequest.of(0, 1);
        var result = repository.findIdByStatusNotIn(statuses, pageRequest);

        if (result.isEmpty())
            return Optional.empty();
        else
            return Optional.of(result.getContent().get(0));
    }

    @Override
    public void changeStatusFor(Long ticketId, TicketStatus newStatus) {
        repository.updateStatusById(ticketId, newStatus);
    }
}
