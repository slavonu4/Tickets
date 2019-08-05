package org.service.tickets.presentation.controller;

import io.swagger.annotations.*;
import org.service.tickets.business.TicketsService;
import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.presentation.dto.NewTicketDTO;
import org.service.tickets.presentation.dto.ValidationFailedMessages;
import org.service.tickets.presentation.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tickets")
@Api("Tickets")
public class TicketsController {
    private final TicketsService service;

    public TicketsController(TicketsService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation("Create a new ticket")
    @ApiResponses({
            @ApiResponse(code = 200, message = "New ticket was created", response = Long.class),
            @ApiResponse(code = 400, message = "New message wasn`t created because of validation errors", response = ValidationFailedMessages.class)
    })
    public ResponseEntity<Long> createTicket(
            @ApiParam(value = "Info for a new ticket", required = true) @Valid @RequestBody NewTicketDTO newTicketInfo,
            BindingResult validationResult
    ) throws ValidationFailedException {
        var newTicketId = service.createTicket(newTicketInfo, validationResult);
        return new ResponseEntity<>(newTicketId, HttpStatus.OK);
    }

    @GetMapping("/{id}/status")
    @ApiOperation("Get a status of the ticket")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Status of the ticket was successfully found", response = TicketStatus.class),
            @ApiResponse(code = 404, message = "Requested ticket wasn`t found")
    })
    public ResponseEntity<TicketStatus> getStatusOf(
            @ApiParam(value = "Id of the ticket", required = true) @PathVariable("id") Long ticketId
    ) {
        var statusOpt = service.getStatusOf(ticketId);

        return ResponseEntity.of(statusOpt);
    }
}
