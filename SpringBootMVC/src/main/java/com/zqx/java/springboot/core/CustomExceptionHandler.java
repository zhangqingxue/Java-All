package com.zqx.java.springboot.core;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * 处理所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    String handleException(Exception e){
        System.out.println(e.getMessage());
        return e.getMessage();

    }

//    /**
//     * 处理所有业务异常
//     */
//    @ExceptionHandler(BusinessException.class)
//    @ResponseBody
//    String handleBusinessException(BusinessException e){
//        System.out.println("BusinessException");
//        return "BusinessException";
//    }

}
