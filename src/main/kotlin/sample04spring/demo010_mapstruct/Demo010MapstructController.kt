package sample04spring.demo010_mapstruct

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/*
build.gradle

    plugins {
        kotlin("kapt") version "1.7.10"
    }

    dependencies {
        kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
        kaptTest("org.mapstruct:mapstruct-processor:1.5.3.Final")
        implementation("org.mapstruct:mapstruct:1.5.3.Final")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    }

Gradle > Demo > Task > other > compileJava (double click)
 */
@RestController
class Demo010MapstructController(
) {

    @GetMapping("/api/demo010")
    fun getMember(): MemberDto {
        val memberVo = MemberVo(
            11L,
            "Frederick",
            "Fred",
            "password11",
            Gender.MALE,
            LocalDateTime.now()
        )

        return MemberVoMapper.INSTANCE.toMemberDto(memberVo)
    }

    @GetMapping("/api/demo010/list")
    fun getMemberList(): List<MemberDto> {
        val memberJon = MemberVo(
            11L,
            "Frederick",
            "Fred",
            "password11",
            Gender.MALE,
            LocalDateTime.now()
        )

        val memberJane = MemberVo(
            12L,
            "Christina",
            "Tina",
            "password12",
            Gender.FEMALE,
            LocalDateTime.now()
        )

        return MemberVoMapper.INSTANCE.toMemberDtoList(listOf(memberJon, memberJane))
    }
}