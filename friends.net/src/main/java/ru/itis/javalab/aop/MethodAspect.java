package ru.itis.javalab.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.AopEntity;
import ru.itis.javalab.aop.service.AopEntityService;

import java.util.Optional;

@Component
@Aspect
public class MethodAspect {
    @Autowired
    private AopEntityService aopEntityService;


    @Pointcut("execution(public * ru.itis.javalab.services.*.*(..))")
    public void processingMethods() {
    }

    @After("processingMethods()")
    public Object saveMethod(JoinPoint joinPoint) {
        Optional<AopEntity> aopEntity = aopEntityService.findByName(joinPoint.getSignature().toString());
        if (aopEntity.isPresent()) {
            aopEntity.get().setCount(aopEntity.get().getCount() + 1);
            aopEntityService.save(aopEntity.get());
        } else {
            aopEntityService.save(AopEntity.builder()
                    .count(1L)
                    .id(null)
                    .name(joinPoint.getSignature().toString())
                    .build());
        }
        return null;
    }
}


