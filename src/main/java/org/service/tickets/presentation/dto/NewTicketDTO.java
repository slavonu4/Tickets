package org.service.tickets.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NewTicketDTO {
    private String routeNumber;
    private LocalDateTime departureDate;

    public NewTicketDTO() {
    }

    public NewTicketDTO(String routeNumber, LocalDateTime departureDate) {
        this.routeNumber = routeNumber;
        this.departureDate = departureDate;
    }

    @JsonProperty("routeNumber")
    @Length(min = 1, max = 20)
    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    @NotNull
    @JsonProperty("departureDate")
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }
}
