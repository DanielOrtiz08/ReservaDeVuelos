package edu.unimagdalena.reservadevuelo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException{
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessages> resourceNotFoundHandler(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessages errorMessages = new ErrorMessages(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessages);
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ErrorMessages> validationExceptionHandler(ValidationException ex, WebRequest request) {
        ErrorMessages errorMessages = new ErrorMessages(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    @ExceptionHandler(value = DuplicateResourceException.class)
    public ResponseEntity<ErrorMessages> duplicateResourceExceptionHandler(DuplicateResourceException ex, WebRequest request) {
        ErrorMessages errorMessages = new ErrorMessages(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages);
    }

}
