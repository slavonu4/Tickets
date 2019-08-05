package org.service.tickets.presentation.controller;

import org.service.tickets.business.TicketsService;
import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.presentation.dto.NewTicketDTO;
import org.service.tickets.presentation.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketsController {
    private final TicketsService service;

    public TicketsController(TicketsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> createTicket(
            @Valid @RequestBody NewTicketDTO newTicketInfo,
            BindingResult validationResult
    ) throws ValidationFailedException {
        if (validationResult.hasErrors())
            throw new ValidationFailedException(validationResult);

        var newTicketId = service.createTicket(newTicketInfo);
        return new ResponseEntity<>(newTicketId, HttpStatus.OK);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<TicketStatus> getStatusOf(@PathVariable("id") Long ticketId) {
        var statusOpt = service.getStatusOf(ticketId);

        return ResponseEntity.of(statusOpt);
    }
}
