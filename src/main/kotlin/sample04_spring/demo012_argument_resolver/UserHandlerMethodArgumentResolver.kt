package sample04_spring.demo012_argument_resolver

import data.UserVo
import org.springframework.core.MethodParameter
import org.springframework.messaging.Message
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver

class UserHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return UserVo::class.java.isAssignableFrom(parameter.parameterType)
    }

    override fun resolveArgument(parameter: MethodParameter, message: Message<*>): Any? {
        // val user: User = from db or session ...

        return UserVo().apply {
            id = 20
            name = "Tweny"
            age = 20
        }
    }
}