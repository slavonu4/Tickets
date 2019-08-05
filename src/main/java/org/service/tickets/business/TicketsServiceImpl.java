package org.service.tickets.business;

import org.service.tickets.domain.dao.TicketsDAO;
import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.presentation.dto.NewTicketDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TicketsServiceImpl implements TicketsService {
    private final TicketsDAO dao;

    public TicketsServiceImpl(TicketsDAO dao) {
        this.dao = dao;
    }

    @Override
    public Long createTicket(NewTicketDTO newTicketInfo) {
        var model = new Ticket(newTicketInfo.getRouteNumber(), newTicketInfo.getDepartureDate());
        var savedModel = dao.save(model);

        return savedModel.getId();
    }

    @Override
    public Optional<TicketStatus> getStatusOf(Long ticketId) {
        return dao.findStatusById(ticketId);
    }
}
