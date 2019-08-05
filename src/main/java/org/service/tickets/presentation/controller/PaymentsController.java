package org.service.tickets.presentation.controller;

import io.swagger.annotations.*;
import org.service.tickets.business.PaymentsService;
import org.service.tickets.domain.model.TicketStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
@Api("Payments")
public class PaymentsController {
    private final PaymentsService service;

    public PaymentsController(PaymentsService service) {
        this.service = service;
    }

    @PostMapping("/process/{ticketId}")
    @ApiOperation("Submit a ticket for processing")
    @ApiResponses(
            @ApiResponse(code = 200, message = "New status for the ticket" , response = TicketStatus.class)
    )
    public ResponseEntity<TicketStatus> processTicket(
            @ApiParam(value = "Id of the ticket", required = true) @PathVariable("ticketId") Long ticketId
    ) {
        TicketStatus result = service.processTicket(ticketId);

        return ResponseEntity.ok(result);
    }
}
