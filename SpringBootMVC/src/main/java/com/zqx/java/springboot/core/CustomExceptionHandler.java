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
    APIResponseResult exceptionHandler(Exception e) {
        System.out.println(e.getMessage());
        return APIResponseResult.ofFail(APIResponseResult.REQUEST_ERROR, e.getMessage());
    }

    /**
     * 处理SQL异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    APIResponseResult sqlExceptionHandler(SQLException e) {
        System.out.println(e.getMessage());
        return APIResponseResult.ofFail(APIResponseResult.REQUEST_ERROR, e.getMessage());

    }

    /**
     * 处理SQL异常
     */
    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseBody
    APIResponseResult MybatisSysExceptionHandler(MyBatisSystemException e) {
        System.out.println(e.getMessage());
        return APIResponseResult.ofFail(APIResponseResult.REQUEST_ERROR, e.getMessage());
    }

    /**
     * 处理所有 @Valid @RequestBody 注解异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    APIResponseResult validExceptionHandler(MethodArgumentNotValidException e){
        System.out.println("MethodArgumentNotValidException");
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        System.out.println(error.getDefaultMessage());
        return APIResponseResult.ofFail(APIResponseResult.REQUEST_ERROR, e.getMessage());
    }



    /**
     * 处理所有@Validated注解在方法参数上的异常(@Validated / @Valid User users)
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    APIResponseResult bindExceptionHandler(BindException e){
        System.out.println("BindException");
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        System.out.println(error.getField());
        System.out.println(error.getObjectName());
        System.out.println(error.getDefaultMessage());
        return APIResponseResult.ofFail(APIResponseResult.REQUEST_ERROR, e.getMessage());
    }



//    /**
//     * 处理所有 @Validated 注解异常, 目前注释掉 先用exceptionHandler()去处理
//     */
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseBody
//    APIResponseResult validatedExceptionHandler(ConstraintViolationException e){
//        System.out.println("MethodArgumentNotValidException");
//        Iterator<ConstraintViolation<?>> error = e.getConstraintViolations().iterator();
//        while (error.hasNext()) {
//            ConstraintViolation<?> next = error.next();
//            System.out.println(next.getMessageTemplate());
//            System.out.println(next.getConstraintDescriptor());
//            System.out.println(next.getMessage());
//        }
//        return APIResponseResult.ofFail(APIResponseResult.REQUEST_ERROR, e.getMessage());
//    }

}
