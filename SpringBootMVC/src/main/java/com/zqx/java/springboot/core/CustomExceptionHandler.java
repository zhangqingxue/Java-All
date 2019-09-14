package com.zqx.java.springboot.core;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

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
     * 处理SQL异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    Response sqlExceptionHandler(SQLException e) {
        System.out.println(e.getMessage());
        Response response = new Response(Response.SERVICE_ERROR, e.getMessage());
        return response;

    }

    /**
     * 处理SQL异常
     */
    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseBody
    Response MybatisSysExceptionHandler(MyBatisSystemException e) {
        System.out.println(e.getMessage());
        Response response = new Response(Response.SERVICE_ERROR, e.getMessage());
        return response;

    }

    /**
     * 处理所有 @Valid @RequestBody 注解异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Response validExceptionHandler(MethodArgumentNotValidException e){
        System.out.println("MethodArgumentNotValidException");
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        System.out.println(error.getDefaultMessage());
        Response response = new Response(Response.REQUEST_ERROR, error.getDefaultMessage());
        return response;
    }



    /**
     * 处理所有@Validated注解在方法参数上的异常(@Validated / @Valid User users)
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    Response bindExceptionHandler(BindException e){
        System.out.println("BindException");
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        System.out.println(error.getField());
        System.out.println(error.getObjectName());
        System.out.println(error.getDefaultMessage());
        Response response = new Response(Response.REQUEST_ERROR, error.getDefaultMessage());
        return response;
    }



//    /**
//     * 处理所有 @Validated 注解异常, 目前注释掉 先用exceptionHandler()去处理
//     */
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseBody
//    Response validatedExceptionHandler(ConstraintViolationException e){
//        System.out.println("MethodArgumentNotValidException");
//        Iterator<ConstraintViolation<?>> error = e.getConstraintViolations().iterator();
//        while (error.hasNext()) {
//            ConstraintViolation<?> next = error.next();
//            System.out.println(next.getMessageTemplate());
//            System.out.println(next.getConstraintDescriptor());
//            System.out.println(next.getMessage());
//        }
//        Response response = new Response(Response.REQUEST_ERROR, e.toString());
//        return response;
//    }

}
