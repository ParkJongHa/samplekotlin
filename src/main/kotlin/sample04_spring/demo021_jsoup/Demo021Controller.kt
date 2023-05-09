package sample04_spring.demo021_jsoup

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo021Controller {

    @GetMapping("/api/demo020")
    fun getOgTag(): String {
        val url = "http://www.molit.go.kr/portal.do"
        return OgTag().getTitle(url)?:"null"
        // http 만 됨
        // https
    }

}