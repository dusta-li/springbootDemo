package com.lqh.springbootdemo.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.lqh.springbootdemo"})
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception{
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode(ErrorInfo.SUCCESS);
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(req.getRequestURI());
        return errorInfo;
    }
}
