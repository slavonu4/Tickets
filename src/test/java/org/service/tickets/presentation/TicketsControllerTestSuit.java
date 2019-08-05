package org.service.tickets.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.service.tickets.business.TicketsService;
import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;
import org.service.tickets.presentation.controller.TicketsController;
import org.service.tickets.presentation.dto.NewTicketDTO;
import org.service.tickets.presentation.exception.ValidationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = TicketsController.class)
public class TicketsControllerTestSuit {
    @MockBean
    private TicketsService ticketsService;

    @Autowired
    private MockMvc mvcMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getStatusOfNotExistingTicket() throws Exception {
        when(ticketsService.getStatusOf(anyLong())).thenReturn(Optional.empty());

        mvcMock.perform(get("/tickets/1/status"))
                .andExpect(status().isNotFound());

        verify(ticketsService).getStatusOf(anyLong());
    }

    @Test
    public void getStatusOfExistingTicket() throws Exception {
        long requestedTicket = 1L;
        TicketStatus response = TicketStatus.ERROR;
        when(ticketsService.getStatusOf(eq(requestedTicket))).thenReturn(Optional.of(response));

        mvcMock.perform(get("/tickets/1/status"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        verify(ticketsService).getStatusOf(eq(requestedTicket));
    }

    @Test
    public void successfullyCreateTicket() throws Exception {
        NewTicketDTO request = new NewTicketDTO("SomeRoute", LocalDateTime.now());
        when(ticketsService.createTicket(any(), any())).thenReturn(1L);

        mvcMock.perform(
                post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(1L)));

        verify(ticketsService).createTicket(any(), any());
    }
}
