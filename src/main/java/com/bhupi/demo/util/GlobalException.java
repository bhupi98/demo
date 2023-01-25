package com.bhupi.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalException {
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<ErrorResponse> handleMyException(CustomException ex) {
//        System.out.println("exxxx"+ex);
//        ErrorResponse error = new ErrorResponse("CustomException", ex.getMessage());
//        System.out.println("error"+error);
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> resourceNotFoundException(CustomException ex) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), ex.getStackTrace()[0].toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), ex.getStackTrace()[0].toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
