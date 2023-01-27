package sample01_kotlin.demo001_dft.ex002_declaredMemberProperties

import kotlin.reflect.full.declaredMemberProperties

fun main1() {
    println("declaredMemberProperties start")

    UserEntity::class.declaredMemberProperties.forEach {
        println(it)
    }

    println("declaredMemberProperties end")
}
