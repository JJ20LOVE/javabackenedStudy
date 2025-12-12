package com.example.demo.exception;

import com.example.demo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    public Result handle(Exception e){
        log.error("服务器异常",e);
        return Result.error("服务器异常");
    }
    
}
