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
public class APIResponseResult {

    public static final int REQUEST_NORMAL = 1000;
    public static final int REQUEST_ERROR = 1001;
    public static final int SERVICE_ERROR = 5001;

    private APIResponseResult(int code,Object result,  String description){
        this.code = code;
        this.result = result;
        this.description = description;
    }

    public static APIResponseResult ofSuccess(Object result) {
        return new APIResponseResult(1000, result, null);
    }

    public static APIResponseResult ofFail(){
        return new APIResponseResult(REQUEST_ERROR, null, "请求错误");
    }

    public static APIResponseResult ofFail(int code, String description) {
        return new APIResponseResult(code, null, description);
    }

    /**
     * 状态码： 1000 请求正常; 1001 请求错误;  5001 服务器错误
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
