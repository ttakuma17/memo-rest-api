package com.rtjavamemoapp.application.controller;

import com.rtjavamemoapp.domain.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MemoControllerAdvice {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss Z");
    private final String EXCEPTION_OCCURRED_TIME = ZonedDateTime.now().format(formatter).toString();

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", EXCEPTION_OCCURRED_TIME,
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI()
        );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("入力値エラー", errors);
        return errorResponse;
    }
}
