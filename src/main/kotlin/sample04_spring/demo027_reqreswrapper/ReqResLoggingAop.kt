package sample04_spring.demo027_reqreswrapper

import com.google.gson.Gson
import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.io.IOUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.util.ContentCachingRequestWrapper
import java.util.*

@Aspect
@Component
class ReqResLoggingAop {

    val reqResLogger = LoggerFactory.getLogger(ReqResLoggingAop::class.java)

    private final val gson: Gson = Gson()

    companion object {
        const val USER_ID_MDC = "USER_ID"
        const val TRACE_ID_MDC = "TRACE_ID"
    }

    @Pointcut("execution(* sample04_spring.demo027_reqreswrapper.Demo027UserController.*(..) )")
    fun onReqLogging() {}

    @Around("sample04_spring.demo027_reqreswrapper.ReqResLoggingAop.onReqLogging()")
    fun requestLogging(proceedingJoinPoint: ProceedingJoinPoint): Any {
        MDC.clear()
        MDC.put(USER_ID_MDC, "userId") // get from session etc
        MDC.put(TRACE_ID_MDC, UUID.randomUUID().toString()) // time instanceNo random

        val startTime = System.currentTimeMillis()
        var obj: Any? = null

        val req: HttpServletRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request

        if (req is ContentCachingRequestWrapper) {
            if ( (req as ContentCachingRequestWrapper).request is CachingRequestWrapper ) {
                val cachingReq = (req as ContentCachingRequestWrapper).request as CachingRequestWrapper
                val param = IOUtils.toString(cachingReq.inputStream, cachingReq.encoding)
                reqResLogger.info("${cachingReq.method}, ${cachingReq.requestURI}, $param")
            } else {
                reqResLogger.info("${req.method}, ${req.requestURI}")
            }
        } else {
            reqResLogger.info("${req.method}, ${req.requestURI}")
        }

        try {
            obj = proceedingJoinPoint.proceed()
            return obj
        } catch (e: Exception) {
            e.printStackTrace()
            obj = proceedingJoinPoint.proceed(proceedingJoinPoint.args)
            return obj
        } finally {
            var returnParam = ""
            try {
                if (obj != null) returnParam = gson.toJson(obj)
            } catch (e1: Exception) {
                returnParam = e1.message?:""
            }
            reqResLogger.info("${req.method}, ${req.requestURI}, $returnParam")
            reqResLogger.info("Delay (${System.currentTimeMillis()-startTime})mSec ${req.method}, ${req.requestURI}, $returnParam")
        }

    }

}