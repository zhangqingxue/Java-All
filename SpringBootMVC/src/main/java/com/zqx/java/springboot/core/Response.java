package com.zqx.java.springboot.core;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 响应报文对象
 */
@Validated
public class Response {

    public static final int NORMAL = 1000;
    public static final int ERROR = 1001;

    public Response(){}

    public Response(int code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 状态码： 1000 正常; 1001 异常
     */
    @NotNull
    @Min(1000)
    @Max(1004)
    private int code;
    @Size(min = 0, max = 15)
    private String description; // 描述
    private Object result; // 结果集

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}