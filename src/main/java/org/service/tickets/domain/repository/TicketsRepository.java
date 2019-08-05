package org.service.tickets.domain.repository;

import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketsRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t.status FROM Ticket t WHERE t.id=:id")
    Optional<TicketStatus> findStatusById(@Param("id") Long ticketId);
}
