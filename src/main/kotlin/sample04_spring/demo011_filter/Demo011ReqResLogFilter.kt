package sample04_spring.demo011_filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class Demo011ReqResLogFilter : OncePerRequestFilter() {

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		println("Demo011ReqResLogFilter request.contextPath:${request.contextPath}, request.servletPath:${request.servletPath}")

		filterChain.doFilter(request, response)
	}

}