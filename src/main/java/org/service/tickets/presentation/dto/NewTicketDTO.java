package org.service.tickets.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@ApiModel(description = "Info for a new ticket")
public class NewTicketDTO {
    @ApiModelProperty(value = "Number of the route", required = true)
    private String routeNumber;
    @ApiModelProperty(value = "Departure date and time", required = true)
    private LocalDateTime departureDate;

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
