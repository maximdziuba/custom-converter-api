package com.converter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CurrencyExceptionHandler {

//    @ExceptionHandler(value = {NoSuchCurrencyException.class})
//    public ResponseEntity<Object> handleNoSuchCurrencyException(NoSuchCurrencyException ex) {
//        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//        CurrencyException currencyException = new CurrencyException(
//                ex.getMessage(),
//                ex,
//                badRequest,
//                ZonedDateTime.now()
//        );
//        return new ResponseEntity<>(currencyException, badRequest);
//    }

    @ExceptionHandler(value = {NoSuchCurrencyException.class})
    public String handleNoSuchCurrencyException(NoSuchCurrencyException ex, Model model) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CurrencyException currencyException = new CurrencyException(
                ex.getMessage(),
                ex,
                badRequest,
                ZonedDateTime.now()
        );
        model.addAttribute("currencyException", currencyException);
        model.addAttribute("status", badRequest);
        model.addAttribute("message", currencyException.getMessage());
        return "error";
    }

    @ExceptionHandler(value = {EmptyCurrencyFieldException.class})
    public String handleEmptyCurrencyFieldException(EmptyCurrencyFieldException ex, Model model) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CurrencyException currencyException = new CurrencyException(
                ex.getMessage(),
                ex,
                badRequest,
                ZonedDateTime.now()
        );
        model.addAttribute("currencyException", currencyException);
        model.addAttribute("status", badRequest);
        model.addAttribute("message", currencyException.getMessage());
        return "error";
    }
}
