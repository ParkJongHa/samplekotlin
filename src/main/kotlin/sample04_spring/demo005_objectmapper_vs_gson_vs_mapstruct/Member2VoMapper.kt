package sample04_spring.demo005_objectmapper_vs_gson_vs_mapstruct

import data.Member2Vo
import data.MemberDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(
    componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class Member2VoMapper {

    companion object {
        var INSTANCE: Member2VoMapper = Mappers.getMapper(Member2VoMapper::class.java)
    }

    abstract fun toMemberDtoList(memberVoList: List<Member2Vo>): List<MemberDto>

}