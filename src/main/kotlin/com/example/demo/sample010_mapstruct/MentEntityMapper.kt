package com.example.demo.sample010_mapstruct

import com.funnyple.backend.biz.ment.data.MentDto
import com.funnyple.backend.biz.ment.data.MentEntity
import com.funnyple.time.TimeUtil
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(
    componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class MentEntityMapper {

    companion object {
        var INSTANCE: MentEntityMapper = Mappers.getMapper(MentEntityMapper::class.java)
    }

    abstract fun toMentDto(mentEntity: MentEntity): MentDto

    abstract fun toMentDtoList(mentEntityList: List<MentEntity>): List<MentDto>

    @AfterMapping
    fun afterMapping(mentEntity: MentEntity, @MappingTarget mentDto: MentDto) {
        mentDto.createdAt =
            if (null==mentEntity.createdAt) null
            else TimeUtil.Companion.Pattern.formatterYmdhms_asSimpleDateFormat.format(mentEntity.createdAt!!)  // todo mapper 에서 자동 매핑으로 변경

        mentDto.updatedAt =
            if (null==mentEntity.updatedAt) null
            else TimeUtil.Companion.Pattern.formatterYmdhms_asSimpleDateFormat.format(mentEntity.updatedAt!!)

        mentDto.deletedAt =
            if (null==mentEntity.deletedAt) null
            else TimeUtil.Companion.Pattern.formatterYmdhms_asSimpleDateFormat.format(mentEntity.deletedAt!!)
    }

    @AfterMapping
    fun afterMapping(mentEntityList: List<MentEntity>, @MappingTarget mentDtoList: List<MentDto>) {
        for(i in mentEntityList.indices) {
            mentDtoList[i].createdAt =
                if (null==mentEntityList[i].createdAt) null
                else TimeUtil.Companion.Pattern.formatterYmdhms_asSimpleDateFormat.format(mentEntityList[i].createdAt!!)  // todo mapper 에서 자동 매핑으로 변경

            mentDtoList[i].updatedAt =
                if (null==mentEntityList[i].updatedAt) null
                else TimeUtil.Companion.Pattern.formatterYmdhms_asSimpleDateFormat.format(mentEntityList[i].updatedAt!!)

            mentDtoList[i].deletedAt =
                if (null==mentEntityList[i].deletedAt) null
                else TimeUtil.Companion.Pattern.formatterYmdhms_asSimpleDateFormat.format(mentEntityList[i].deletedAt!!)
        }

    }


}