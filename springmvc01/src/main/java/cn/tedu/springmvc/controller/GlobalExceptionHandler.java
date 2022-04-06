package cn.tedu.springmvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            NullPointerException.class,
            ClassCastException.class
    })
    public String handleNullPointerException(Throwable e) {
        return "Error, NullPointerException or ClassCastException!";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(Throwable e) {
        return "Error, NumberFormatException!";
    }

    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable e) {
        return "Error, Throwable!";
    }

}
