package com.spring.demo.exception;

import com.spring.demo.helper.MessageResponse;
import com.spring.demo.service.bundleMessage.BundleMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final BundleMessageService bundleMessageService;

    public GlobalExceptionHandler(
            BundleMessageService bundleMessageService
    ) {
        this.bundleMessageService = bundleMessageService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageResponse> handleResourceNotFound(
            ResourceNotFoundException exception
    ) {
        MessageResponse response = bundleMessageService.getMessage(
                exception.getMessageCode(),
                exception.getArgs()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<MessageResponse>> handleValidation(
            MethodArgumentNotValidException exception
    ) {
        List<FieldError> fieldErrors = exception
                .getBindingResult()
                .getFieldErrors();

        List<MessageResponse> messages = fieldErrors
                .stream()
                .map(fieldError -> bundleMessageService.getMessage(
                        fieldError.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(messages);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleGeneralException(
            Exception exception
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(bundleMessageService.getMessage(
                        "general.unexpected.error"
                ));
    }
}
