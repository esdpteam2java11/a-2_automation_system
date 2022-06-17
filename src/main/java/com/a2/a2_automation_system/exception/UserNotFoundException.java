package com.a2.a2_automation_system.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
    private String message;


}
