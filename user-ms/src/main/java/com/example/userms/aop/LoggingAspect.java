package com.example.userms.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.example.userms.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Start method {}", joinPoint.getSignature().getName());//method name
    }

    @After("execution(* com.example.userms.service.*.*(..))")
    public void after(JoinPoint joinPoint) {
        log.info("Start method {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing("execution(*  com.example.userms.service.*.*(..))")
    public Object afterThrowing(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start method {}", joinPoint.getSignature().getName());
        return joinPoint.proceed();
    }

    @Around("execution(*  com.example.userms.service.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Start method {}", proceedingJoinPoint.getSignature().getName());
        String result = (String) proceedingJoinPoint.proceed();
        log.info("End method {}", proceedingJoinPoint.getSignature().getName());
        return result;
    }
}
