package org.study.todobackend.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class TestAop {

    @Around("execution(* org.study.todobackend.domain.todo.service.*(..))")
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint){
        println("joinpoint")
        joinPoint.proceed()
    }
}