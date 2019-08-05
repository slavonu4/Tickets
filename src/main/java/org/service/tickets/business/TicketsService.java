package org.service.tickets.business;

import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.presentation.dto.NewTicketDTO;
import org.service.tickets.presentation.exception.ValidationFailedException;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface TicketsService {
    Long createTicket(NewTicketDTO newTicketInfo, BindingResult validationResult) throws ValidationFailedException;

    Optional<TicketStatus> getStatusOf(Long ticketId);
}
