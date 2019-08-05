package org.service.tickets.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValidationFailedMessages{
    public static class ValidationFailedMessage {
        private final String field;
        private final String message;


        public ValidationFailedMessage(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @JsonProperty("field")
        public String getField() {
            return field;
        }

        @JsonProperty("message")
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
