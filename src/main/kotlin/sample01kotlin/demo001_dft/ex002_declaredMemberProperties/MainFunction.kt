package sample01kotlin.demo001_dft.ex002_declaredMemberProperties

import kotlin.reflect.full.declaredMemberProperties

fun main() {
    println("declaredMemberProperties start")

    UserEntity::class.declaredMemberProperties.forEach {
        println(it)
    }

    println("declaredMemberProperties end")
}
