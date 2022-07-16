package com.xiaosuange.aop;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosuange.exception.SqlFailureException;
import com.xiaosuange.exception.TokenInvalidException;
import com.xiaosuange.mapper.UserMapper;
import com.xiaosuange.pojo.Users;
import org.apache.catalina.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAdvice {
    @Autowired
    private UserMapper userMapper;

    @Pointcut("execution(* com.xiaosuange.controller..*(..))")
    private void checkTokenpointcut() {
    }

    @Around("checkTokenpointcut()")
    public Object checkToken(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("enter checktoken aspect");
        Object res=null;
        Object[] args = pjp.getArgs();
        String token = null;
        for (Object o : args) {
            if (o instanceof String && ((String) o).matches(".*;,\\d{10}")) {
                token = (String) o;
            }
        }
        if (token == null || checkToken(token)) {
            System.out.println("checktoken passed");
            try {
                res = pjp.proceed();
            } catch (Throwable e) {
                System.out.println("catch err");
                e.printStackTrace();
                throw new SqlFailureException();
            }
        } else {
            throw new TokenInvalidException();
        }
        return res;
    }

    private boolean checkToken(String token) {
        return userMapper.selectByToken(token) != null;
    }
}
