package org.service.tickets.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import org.service.tickets.presentation.deserializer.LocalDateTimeDeserializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NewTicketDTO {
    private final String routeNumber;
    private final LocalDateTime departureDate;

    public NewTicketDTO(String routeNumber, LocalDateTime departureDate) {
        this.routeNumber = routeNumber;
        this.departureDate = departureDate;
    }

    @JsonProperty("routeNumber")
    @Length(min = 1, max = 20)
    public String getRouteNumber() {
        return routeNumber;
    }

    @NotNull
    @JsonProperty("departureDate")
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }
}
