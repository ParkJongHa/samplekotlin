package sample01_kotlin.demo001_dft.ex007_stream_sum

import common.TempData

fun main1() {

    val avgAge = TempData.userVoList.sumOf { it.age?:0 } / TempData.userVoList.size

    println("avgAge : $avgAge")

}
