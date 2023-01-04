package com.example.demo.sample010_mapstruct

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class Sample010MapstructController(
) {

    @GetMapping("/api/sample010")
    fun getMember(): MemberDto? {
        val memberVo = MemberVo(
            12L,
            "Jone",
            "password12",
            Gender.MALE,
            LocalDateTime.now()
        )

        val memberDto = MemberDto() // todo

        return memberDto
    }

    @GetMapping("/api/sample010/list")
    fun getMemberList(): List<MemberDto> {
        val memberVo = MemberVo(
            12L,
            "Jone",
            "password12",
            Gender.MALE,
            LocalDateTime.now()
        )

        val memberDto = MemberDto() // todo

        return listOf(memberDto) // list
    }

}