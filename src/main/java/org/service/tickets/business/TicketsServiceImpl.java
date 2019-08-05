package org.service.tickets.business;

import org.service.tickets.domain.dao.TicketsDAO;
import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.presentation.dto.NewTicketDTO;
import org.service.tickets.presentation.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@Transactional
public class TicketsServiceImpl implements TicketsService {
    private final TicketsDAO dao;

    public TicketsServiceImpl(TicketsDAO dao) {
        this.dao = dao;
    }

    @Override
    public Long createTicket(NewTicketDTO newTicketInfo, BindingResult validationResult) throws ValidationFailedException {
        if (validationResult.hasErrors())
            throw new ValidationFailedException(validationResult);

        Ticket model = new Ticket(newTicketInfo.getRouteNumber(), newTicketInfo.getDepartureDate());
        Ticket savedModel = dao.save(model);

        return savedModel.getId();
    }

    @Override
    public Optional<TicketStatus> getStatusOf(Long ticketId) {
        return dao.findStatusById(ticketId);
    }
}
