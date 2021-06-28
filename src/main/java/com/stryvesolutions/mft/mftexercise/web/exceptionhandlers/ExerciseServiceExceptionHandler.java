package com.stryvesolutions.mft.mftexercise.web.exceptionhandlers;

import com.stryvesolutions.mft.mftexercise.web.model.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
// Advices spring that this is a exception handler. Global exception handler.
@ControllerAdvice
public class ExerciseServiceExceptionHandler {

    // Exception handler is applied globally.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleException(IllegalArgumentException exception) {
        ErrorDTO errorDto = ErrorDTO.builder().errorCode("BAD_REQUEST").errorDescription(new ArrayList<>()).build();
        errorDto.getErrorDescription().add(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_IMPLEMENTED);
    }
}
