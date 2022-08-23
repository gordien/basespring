package ru.gordy.gascrudapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class BenchmarkAspect {

    public BenchmarkAspect() {
    }

    @Around("@annotation(ru.gordy.gascrudapi.annotation.Benchmark)")
    public Object getExecutionTime(ProceedingJoinPoint jpoint) throws Throwable {
        Object result;
        long startTime = System.currentTimeMillis();
        try {
            result = jpoint.proceed();
        } catch (Exception e) {
            result = null;
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("method " + jpoint.getSignature().getName() + " with args"  + Arrays.toString(jpoint.getArgs()) + " completed in " + executionTime + " ms");
        return result;







    }
}
