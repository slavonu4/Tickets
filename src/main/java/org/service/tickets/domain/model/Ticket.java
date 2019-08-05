package org.service.tickets.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Ticket.TABLE_NAME)
public class Ticket {
    static final String TABLE_NAME = "tickets";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROUTE_NUMBER = "route_number";
    private static final String COLUMN_DEPARTURE_DATE = "departure_date";
    private static final String COLUMN_STATUS = "status";

    private Long id;
    private String routeNumber;
    private LocalDateTime departureDate;
    private TicketStatus status = TicketStatus.PENDING;

    public Ticket() {
    }

    public Ticket(String routeNumber, LocalDateTime departureDate) {
        this.routeNumber = routeNumber;
        this.departureDate = departureDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = COLUMN_ROUTE_NUMBER)
    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    @Column(name = COLUMN_DEPARTURE_DATE)
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    @Column(name = COLUMN_STATUS)
    @Enumerated(EnumType.STRING)
    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
