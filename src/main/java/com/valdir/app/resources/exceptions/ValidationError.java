package com.valdir.app.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    @Getter
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(int status, long timestamp, String message) {
        super(status, timestamp, message);
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }

}
