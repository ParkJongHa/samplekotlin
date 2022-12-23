package com.example.demo.sample009_aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Component
@Aspect
class Sample009AopLogger {

    @Pointcut("execution(* com..*Controller.*(..) )")
    fun controllerPointcut() {}

    @Around("controllerPointcut()")
    fun log(proceedingJoinPoint: ProceedingJoinPoint): Any? {

        proceedingJoinPoint.args.forEach {
            println("proceedingJoinPoint.args > $it")
        }

        return proceedingJoinPoint.proceed()
    }

}