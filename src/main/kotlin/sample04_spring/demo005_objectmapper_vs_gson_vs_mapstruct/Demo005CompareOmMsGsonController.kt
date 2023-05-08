package sample04_spring.demo005_objectmapper_vs_gson_vs_mapstruct

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.GenderEnum
import data.Member2Vo
import data.MemberDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class Demo005CompareOmMsGsonController(
) {

    private final val memberVoList = mutableListOf<Member2Vo>()

    init {
        for (i in 0 until 100000)
            memberVoList.add(Member2Vo(
                i.toLong(),
                "J$i",
                "j$i",
                "ps$i",
                if (Random.nextBoolean()) GenderEnum.FEMALE else GenderEnum.MALE,
            ))
    }

    @GetMapping("/api/demo005")
    fun getCompareResult(): String {
        return "each x${memberVoList.size} delay" +
        "<br>ObjectMapper ${getDelayObjectMapper()}ms" +
        "<br>MapStruct ${getDelayMapStruct()}ms" +
        "<br>Gson ${getDelayGson()}ms (With toJson ${getDelayGson2()}ms)"
    }
/*
each x100000 delay
ObjectMapper    99ms                      123ms                      89ms
MapStruct        4ms                        5ms                       4ms
Gson            85ms (With toJson 220ms)  111ms (With toJson 292ms) 109ms (With toJson 272ms)
 */

    private fun getDelayObjectMapper(): Long {
        val oDelay = System.currentTimeMillis()
        val list: List<MemberDto> =
            ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(memberVoList, object : TypeReference<List<MemberDto>>() {})
//        list.forEach{ println(" o - $it") }

        return System.currentTimeMillis() - oDelay
    }

    private fun getDelayMapStruct(): Long {
        val mDelay = System.currentTimeMillis()
        val list: List<MemberDto> =
            Member2VoMapper.INSTANCE.toMemberDtoList(memberVoList)
//        list.forEach{ println(" m - $it") }

        return System.currentTimeMillis() - mDelay
    }

    private fun getDelayGson(): Long {
        val gString = Gson().toJson(memberVoList)
        val g2Delay = System.currentTimeMillis()
        val list : List<MemberDto> =
            Gson().fromJson(gString, object : TypeToken<List<MemberDto>>() {}.type)
//        list.forEach{ println(" g - $it") }

        return System.currentTimeMillis() - g2Delay
    }

    private fun getDelayGson2(): Long {
        val gDelay = System.currentTimeMillis()
        val list : List<MemberDto> =
            Gson().fromJson(Gson().toJson(memberVoList), object : TypeToken<List<MemberDto>>() {}.type)

//        list.forEach{ println("g2 - $it") }

        return System.currentTimeMillis() - gDelay
    }

}