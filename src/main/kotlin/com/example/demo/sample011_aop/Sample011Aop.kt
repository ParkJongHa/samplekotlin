package com.example.demo.sample011_aop

import com.google.gson.Gson
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest

@Component
@Aspect
class Sample011Aop {

    @Pointcut("execution(* com..*Controller.*(..) )")
    fun controllerPointcut() {}

    @Around("controllerPointcut()")
    fun log(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        println("Req > ${proceedingJoinPoint.target.javaClass.canonicalName.replaceAfterLast("@", "")}" +
                ".${proceedingJoinPoint.signature.name}")

        val startTime = System.currentTimeMillis()

        proceedingJoinPoint.args.forEach {when (it) {
            is WebRequest -> {
                println("ProceedingJoinPoint.args is WebRequest > ${it.contextPath}")
            }
            is Throwable -> {
                println("ProceedingJoinPoint.args is Throwable > ${it.printStackTrace()}")
            }
            else -> {
                println("ProceedingJoinPoint.args is undefined > $it")
            }
        }}

        var returnVal: Any? = null

        try {
            returnVal = proceedingJoinPoint.proceed()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            println("Res > ${proceedingJoinPoint.target.javaClass.canonicalName.replaceAfterLast("@", "")}" +
                    ".${proceedingJoinPoint.signature.name}" +
                    " (${System.currentTimeMillis() - startTime}sec)" +
                    " ${Gson().toJson(returnVal)}")
        }

        return returnVal
    }

}