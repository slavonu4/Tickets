package org.service.tickets.business;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.service.tickets.domain.dao.TicketsDAO;
import org.service.tickets.domain.model.Ticket;
import org.service.tickets.presentation.dto.NewTicketDTO;
import org.service.tickets.presentation.exception.ValidationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TicketsServiceTestSuit {
    @MockBean
    private TicketsDAO dao;

    @Autowired
    private TicketsService ticketsService;

    @Test(expected = ValidationFailedException.class)
    public void preventCreationOfTicketWithFailedValidation() throws ValidationFailedException {
        NewTicketDTO ticket = new NewTicketDTO("test", null);
        BindingResult bindingResultMock = mock(BindingResult.class);
        when(bindingResultMock.hasErrors()).thenReturn(true);

        ticketsService.createTicket(ticket, bindingResultMock);
    }

    @Test
    public void createValidTicket() throws ValidationFailedException {
        NewTicketDTO ticket = new NewTicketDTO("test", LocalDateTime.now());
        ArgumentCaptor<Ticket> ticketModelCaptor = ArgumentCaptor.forClass(Ticket.class);
        BindingResult bindingResultMock = mock(BindingResult.class);
        long newTicketId = 1L;


        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(dao.save(ticketModelCaptor.capture())).thenAnswer(invocation -> {
            Ticket model = ticketModelCaptor.getValue();
            model.setId(newTicketId);

            return model;
        });

        Long result = ticketsService.createTicket(ticket, bindingResultMock);

        Assert.assertEquals(newTicketId, result.longValue());
    }
}
