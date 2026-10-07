package com.mertezer.checkpoint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //Validation hatalarını postmanin anlayacağı json tipine çevirmek
    ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        List<FieldError> errorList = e.getBindingResult().getFieldErrors(); //Spring validationdan geçemeyen her alan için field error nesnesi oluşturur
        for(FieldError error : errorList){
            errors.put(error.getField(),error.getDefaultMessage()); //.getField() ile alan adını
        }                                                          //.getDefaultMessage() ile o alanın validation mesajı alınır
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
