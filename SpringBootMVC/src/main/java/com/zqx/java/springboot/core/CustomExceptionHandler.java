package com.zqx.java.springboot.core;

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
    Response handleException(Exception e) {
        System.out.println(e.getMessage());
        Response response = new Response(Response.NORMAL, e.getMessage());
        return response;

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
