package com.example.demo.sample010_mapstruct

import java.time.LocalDateTime

data class MemberVo(
    var id: Long? = null,
    var name: String? = null,
    var password: String? = null,
    var gender: Gender? = null,
    var lastVisitedAt: LocalDateTime? = null
)