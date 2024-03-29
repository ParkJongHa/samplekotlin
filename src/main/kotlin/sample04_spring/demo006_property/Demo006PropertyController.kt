package sample04_spring.demo006_property

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
application.yml
application-xxx.yml

change active value in application.yml
spring:
  profiles:
    active: release

then you can get manager.id value in application-release.yml

@Value
in java
    @Value("#{'${manager.id}'}")
in kotlin
    @Value("\${manager.id}")
 */
@RestController
class Demo006PropertyController {

    @Value("\${manager.id}")
    lateinit var managerId: String

    @Value("\${manager.name}")
    lateinit var managerName: String

    @Value("\${manager.phone}")
    private lateinit var managerPhone: String

    @GetMapping("/api/demo006")
    fun getPropertyValue(): String {
        return """
            emergency manager
            </br>id : $managerId
            </br>name : $managerName
            </br>phone : $managerPhone
        """
    }

}