package sample01_kotlin.demo010_string.ex004_string_to_code

import data.Depth1Vo
import data.Depth2Vo
import data.MemberVo
import kotlin.reflect.full.memberProperties

/*
null == evt.src || "launcher:id/taskbar" == evt.src?.id
dynamic run
 */
fun main1() {
    val rawExpression = "null == depth1Vo.userVo || \"NineOne\" == depth1Vo.depth2Vo!!.memberVo?.name  || \"NineOne\" == depth1Vo.depth2Vo!!.memberVo?.name  && \"NineOne\" != depth1Vo.depth2Vo!!.memberVo?.name"
    val depth1Vo = Depth1Vo()
        depth1Vo.depth2Vo = Depth2Vo()
        depth1Vo.depth2Vo!!.memberVo = MemberVo(91, "NineOne")

    val result = try {
        getExpressionResult(rawExpression, depth1Vo)
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

    println("result: $result")
}


val memberPrefixRegex = Regex("\\.|\\?\\.|!!\\.") // . or ?. or !!.
val orAndRegex = Regex("\\|\\||&&") // || or &&

fun getExpressionResult(rawExpression: String, cls: Any?): Boolean {
    val orAndRegexSequence: Sequence<MatchResult> = orAndRegex.findAll(rawExpression)
    val expressionList: List<String> = rawExpression.split(orAndRegex)

/*
null == evt.source
"com.sec.android.app.launcher:id/taskbar_container" == evt.source?.viewIdResourceName
null == evt.source1
 */
    val expressionResultList: List<Boolean> = expressionList.map { getOneExpressionResult(it, cls) }
    var finalBooleanResult: Boolean = expressionResultList[0]

    orAndRegexSequence.forEachIndexed { i, matchResult ->
//        tempString = tempString + " " + matchResult.value + " " + splitedRawExpressionResultList[i+1]
        finalBooleanResult =
            if ("||" == matchResult.value) or(finalBooleanResult, expressionResultList[i+1])
            else and(finalBooleanResult, expressionResultList[i+1])
    }
//    println("tempString : $tempString -> $finalBooleanResult")

    return finalBooleanResult
}

/**
 * anExpression "null == evt.source" // 상수를 앞에쓴다. 클래스를 뒤에 쓴다.
 * cls InstanceOfClass
 */
fun getOneExpressionResult(anExpression: String, cls: Any?):Boolean {
    val symbol = if (anExpression.contains("==")) "=="
                 else "!="

    var constantExp: String? = anExpression.split(symbol)[0].trim() // null
    constantExp =
        if ("null" == constantExp) null
        else constantExp?.replace("\"","")

    val instanceExp: String = anExpression.split(symbol)[1].trim() // evt.source
    val instanceExpResult: Any? = getInstanceValue(instanceExp, cls)

    println("aRawExpression:$anExpression, symbol:$symbol, constantExp:$constantExp, instanceExp:$instanceExp")

    return if("=="==symbol) equal(constantExp, instanceExpResult)
           else notEqual(constantExp, instanceExpResult)
}

/**
instanceExp evt.source?.viewIdResourceName
 */
fun getInstanceValue(instanceExp: String, any: Any?): Any? {
    val splittedInstanceExp = instanceExp.split(memberPrefixRegex) // evt  source  viewIdResourceName
    if (2 > splittedInstanceExp.size) return any

    splittedInstanceExp.forEach { println("getInstanceValue - $it")}

    var tempCls:Any? = any
    for (nTh in 1 until splittedInstanceExp.size) {
        tempCls = getInstance(splittedInstanceExp[nTh], tempCls)
        if (null == tempCls) return null
    }
    return tempCls
}

/*
evt.source?.viewIdResourceName
 */
fun getInstance(target: String, cls: Any?): Any? {
    return if (null == cls) null
        else cls::class.memberProperties
            .find { kProperty1 -> kProperty1.name == target }
            ?.getter
            ?.call(cls)
}

fun equal(any1: Any?, any2: Any?): Boolean {
    return any1 == any2
}

fun notEqual(any1: Any?, any2: Any?): Boolean {
    return any1 != any2
}

fun and(b1: Boolean, b2: Boolean): Boolean {
    return b1 && b2
}

fun or(b1: Boolean, b2: Boolean): Boolean {
    return b1 != b2
}