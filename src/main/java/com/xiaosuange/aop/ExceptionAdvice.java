package com.xiaosuange.aop;

import com.xiaosuange.controller.returntype.Feedback;
import com.xiaosuange.exception.SqlFailureException;
import com.xiaosuange.exception.TokenInvalidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(SqlFailureException.class)
    @ResponseBody
    public Feedback sqlFailure(SqlFailureException e){
        e.printStackTrace();
        return Feedback.builder()
                .status_code(-1)
                .status_msg("sql err!\n")
                .build();
    }
    @ExceptionHandler(TokenInvalidException.class)
    @ResponseBody
    public Feedback sqlFailure(TokenInvalidException e){
        e.printStackTrace();
        return Feedback.builder()
                .status_code(-2)
                .status_msg("token is invalid!\n")
                .build();
    }

}
