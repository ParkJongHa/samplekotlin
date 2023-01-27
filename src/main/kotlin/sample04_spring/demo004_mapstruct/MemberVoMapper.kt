package sample04_spring.demo004_mapstruct

import data.MemberDto
import data.MemberVo
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class MemberVoMapper {

    companion object {
        var INSTANCE: MemberVoMapper = Mappers.getMapper(MemberVoMapper::class.java)
    }

    @Mapping(target = "nickname", source = "alias") //
    abstract fun toMemberDto(memberVo: MemberVo): MemberDto

    abstract fun toMemberDtoList(memberVoList: List<MemberVo>): List<MemberDto>

    @AfterMapping
    fun afterMapping(mentVo: MemberVo, @MappingTarget memberDto: MemberDto) {
        memberDto.lastAccessedAt =
            if (null==mentVo.lastVisitedAt) ""
            else mentVo.lastVisitedAt.toString()
    }

    @AfterMapping
    fun afterMapping(memberVoList: List<MemberVo>, @MappingTarget memberDtoList: List<MemberDto>) {
        for(i in memberVoList.indices) {
            memberDtoList[i].lastAccessedAt =
                if (null==memberVoList[i].lastVisitedAt) ""
                else memberVoList[i].lastVisitedAt.toString()
        }
    }

}