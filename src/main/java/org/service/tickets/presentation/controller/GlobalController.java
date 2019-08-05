package org.service.tickets.presentation.controller;

import org.service.tickets.presentation.dto.ValidationFailedMessages;
import org.service.tickets.presentation.exception.ValidationFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalController {
    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<ValidationFailedMessages> validationFailed(ValidationFailedException exception) {
        var validationResults = exception.getValidationResult();
        var validationMessages = getValidationErrorMessages(validationResults);

        return ResponseEntity.badRequest()
                .body(validationMessages);
    }

    private ValidationFailedMessages getValidationErrorMessages(BindingResult validationResults) {
        var messages = validationResults.getFieldErrors().stream()
                .map(error -> new ValidationFailedMessages.ValidationFailedMessage(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationFailedMessages(messages);
    }
}
