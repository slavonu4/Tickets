package org.service.tickets.presentation.controller;

import org.service.tickets.business.PaymentsService;
import org.service.tickets.domain.model.TicketStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
public class PaymentsController {
    private final PaymentsService service;

    public PaymentsController(PaymentsService service) {
        this.service = service;
    }

    @PostMapping("/process/{ticketId}")
    public ResponseEntity<TicketStatus> processTicket(@PathVariable("ticketId") Long ticketId) {
        TicketStatus result = service.processTicket(ticketId);

        return ResponseEntity.ok(result);
    }
}
