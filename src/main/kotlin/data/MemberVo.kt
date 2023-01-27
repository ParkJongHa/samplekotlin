package data

import data.GenderEnum
import java.time.LocalDateTime

data class MemberVo(
    var id: Long? = null,
    var name: String? = null,
    var alias: String? = null,
    var password: String? = null,
    var gender: GenderEnum? = null,
    var lastVisitedAt: LocalDateTime? = null
)