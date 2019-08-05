package org.service.tickets.domain.repository;

import org.service.tickets.domain.model.Ticket;
import org.service.tickets.domain.model.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface TicketsRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t.status FROM Ticket t WHERE t.id=:id")
    Optional<TicketStatus> findStatusById(@Param("id") Long ticketId);

    @Query("SELECT t.id FROM Ticket t WHERE t.status NOT IN :statuses")
    Page<Long> findIdByStatusNotIn(@Param("statuses") Set<TicketStatus> statuses, Pageable pageRequest);

    @Modifying
    @Query("UPDATE Ticket t SET t.status=:status WHERE t.id=:id")
    void updateStatusById(@Param("id") Long ticketId, @Param("status") TicketStatus newStatus);
}
