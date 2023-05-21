package com.example.TestAub.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyTimedAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTimedAspect.class);
    private ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Before("@annotation(com.example.TestAub.utils.MyTimed)")
    public void startTimer(JoinPoint joinPoint) {
        LOGGER.info("Executing method: {}", joinPoint.getSignature().toShortString());
        LOGGER.info("Starting timer...");
        startTime.set(System.currentTimeMillis());
    }

    @After("@annotation(com.example.TestAub.utils.MyTimed)")
    public void stopTimer(JoinPoint joinPoint) {
        long executionTime = System.currentTimeMillis() - startTime.get();
        LOGGER.info("Stopped timer.");
        LOGGER.info("Method execution time: {} ms", executionTime);
        startTime.remove();
    }

}
