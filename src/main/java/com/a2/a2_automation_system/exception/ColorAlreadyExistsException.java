package com.a2.a2_automation_system.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@AllArgsConstructor
@NoArgsConstructor
public class ColorAlreadyExistsException extends RuntimeException {
    private String message;
}
