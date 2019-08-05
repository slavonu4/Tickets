package org.service.tickets.presentation.exception;

import org.springframework.validation.BindingResult;

public class ValidationFailedException extends Exception{
    private final BindingResult validationResult;

    public ValidationFailedException(BindingResult validationResult) {
        this.validationResult = validationResult;
    }

    public BindingResult getValidationResult() {
        return validationResult;
    }
}
