package com.xiaosuange.aop;

import com.xiaosuange.exception.SqlFailureException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAdvice {
    @Pointcut("execution(* com.xiaosuange.service.UserService.*(..))")
    private void userMapperPointcut(){}

    @Around("userMapperPointcut()")
    public Object throwExp(ProceedingJoinPoint pjp) throws SqlFailureException {
        System.out.println("enter aspect");
        Object res = null;
        try {
            res=pjp.proceed();
        } catch (Throwable e){
            System.out.println("aspect throw an exception.");
            throw new SqlFailureException();
        }
        return res;
    }
}
