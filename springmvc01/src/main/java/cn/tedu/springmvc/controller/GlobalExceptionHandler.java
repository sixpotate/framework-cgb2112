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
        e.printStackTrace();
        return "Error, NullPointerException or ClassCastException!";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(Throwable e) {
        e.printStackTrace();
        return "Error, NumberFormatException!";
    }

    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable e) {
        e.printStackTrace();
        return "Error, Throwable!";
    }

}
