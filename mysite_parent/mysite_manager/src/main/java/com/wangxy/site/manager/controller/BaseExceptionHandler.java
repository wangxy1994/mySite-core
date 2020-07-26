package com.wangxy.site.manager.controller;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseBody
    public Result error(ExpiredJwtException e){
        e.printStackTrace();
        System.out.println("token过期");
        return new Result(false, StatusCode.TOKENEXPIREDERROR, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();        
        return new Result(false, StatusCode.TOKENEXPIREDERROR, e.getMessage());
    }


}
