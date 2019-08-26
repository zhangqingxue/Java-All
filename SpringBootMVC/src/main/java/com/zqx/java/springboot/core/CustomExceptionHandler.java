package com.zqx.java.springboot.core;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * 处理所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Response exceptionHandler(Exception e) {
        System.out.println(e.getMessage());
        Response response = new Response(Response.REQUEST_ERROR, e.getMessage());
        return response;

    }

    /**
     * 处理所有validation注解异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Response validExceptionHandler(MethodArgumentNotValidException  e){
        System.out.println("MethodArgumentNotValidException");
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        System.out.println(error.getDefaultMessage());
        Response response = new Response(Response.REQUEST_ERROR, error.getDefaultMessage());
        return response;
    }

}
