package eu.morozik.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MyAspect {

    @Pointcut("execution(public * eu.morozik.historicalplaces.controller..*(..))  ||" +
            "execution(public * eu.morozik.historicalplaces.service..*(..))")
    public void callAtMyServicePublic() {
    }

    @Around("callAtMyServicePublic()")
    public Object aroundCallAt(ProceedingJoinPoint call) throws Throwable {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_PURPLE = "\u001B[35m";
        Long start = System.currentTimeMillis();
        call.proceed();
        Long end = System.currentTimeMillis();
        log.info(ANSI_PURPLE + "Время выполнения метода : " +
                call.getSignature().getDeclaringTypeName() + "." + call.getSignature().
                getName() + "()" + " = " + (end - start) + " миллисекунды" + ANSI_RESET);
        return call.proceed();
    }
}
