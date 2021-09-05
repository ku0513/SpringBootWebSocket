package com.example.websocket.server.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.websocket.server..*(..))")
    public void controllerStartLog(JoinPoint joinPoint) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("[Start] {}, args: {}", string, args);
    }

    @AfterReturning(value = "execution(* com.example.websocket.server..*(..))", returning = "r")
    public void controllerEndLog(JoinPoint joinPoint) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("[End] {}, args: {}", string, args);
    }

    @AfterThrowing(value = "execution(* com.example.websocket.server..*(..))", throwing = "e")
    public void afterException(JoinPoint joinPoint, Exception e) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.error("[Error!], Exception: {}, {}, args = {}", e.getMessage(), string, args);
    }
}
