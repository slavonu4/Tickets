package org.service.tickets.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("List of validation errors")
public class ValidationFailedMessages{

    @ApiModel("Validation error")
    public static class ValidationFailedMessage {
        private final String field;
        private final String message;


        public ValidationFailedMessage(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @JsonProperty("field")
        @ApiModelProperty("Name of the field with a validation error")
        public String getField() {
            return field;
        }

        @JsonProperty("message")
        @ApiModelProperty("Validation error message")
        public String getMessage() {
            return message;
        }
    }

    private final List<ValidationFailedMessage> messages;

    public ValidationFailedMessages(List<ValidationFailedMessage> messages) {
        this.messages = messages;
    }

    @JsonProperty("messages")
    public List<ValidationFailedMessage> getMessages() {
        return messages;
    }
}
