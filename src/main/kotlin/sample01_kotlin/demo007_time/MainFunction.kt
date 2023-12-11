package sample01_kotlin.demo007_time

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.util.*

fun main1() {
    val year = 2000
    val month = 9
    val day = 17

    val localDate = LocalDate.of(year, month, day)

    Locale.getAvailableLocales().forEach {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(it)

        val localizedDate = localDate.format(formatter).trim()
        val ymdSort: String
            =
            if (it.toString() == "") {
                ""
            } else if (localizedDate.startsWith(year.toString())) {
                val mOrD = localizedDate
                    .replace(year.toString(), "")
                    .replace(".","")
                    .replace(",","")
                    .replace("-","")
                    .replace(" ","")
                    .trim()

                if (mOrD.startsWith(day.toString())) {
                    "YDM"
                } else {
                    "YMD"
                }

            } else if (localizedDate.startsWith(day.toString())) {
                val yOrM = localizedDate
                    .replace(day.toString(), "")
                    .replace(".","")
                    .replace(",","")
                    .replace("-","")
                    .replace(" ","")
                    .trim()

                if (yOrM.startsWith(year.toString())) {
                    "DYM"
                } else {
                    "DMY"
                }

            } else if (localizedDate.endsWith(year.toString())) {
                val mOrD = localizedDate
                    .replace(year.toString(), "")
                    .replace(".","")
                    .replace(",","")
                    .replace("-","")
                    .replace(" ","")
                    .trim()

                if (mOrD.startsWith(day.toString())) {
                    "DMY"
                } else {
                    "MDY"
                }

            } else if (localizedDate.endsWith(day.toString())) {
                val yOrM = localizedDate
                    .replace(day.toString(), "")
                    .replace(".","")
                    .replace(",","")
                    .replace("-","")
                    .replace(" ","")
                    .trim()

                if (yOrM.startsWith(year.toString())) {
                    "YMD"
                } else {
                    "MYD"
                }

            } else {
                "???"
            }

        println(
            "%-${20}s".format(it.toString()) +
            "%-${4}s".format(ymdSort) +
            "%-${30}s".format(localizedDate) +
            ""
        )
    }
}

fun main() {
    val zoneId = ZoneId.systemDefault()

    Locale.getAvailableLocales().forEach {
        println("$zoneId ${"%-${20}s".format(it.toString())} ${zoneId.getDisplayName(TextStyle.FULL, it)}")
    }
}
/*
Asia/Seoul my                   ကိုရီးယား အချိန်
Asia/Seoul ka                   კორეის დრო
Asia/Seoul ff_GH_#Adlm          𞤑𞤭𞤶𞤮𞥅𞤪𞤫 𞤑𞤮𞥅𞤪𞤫𞤴𞤢𞥄
Asia/Seoul ko_KR_#Kore          대한민국 시간
Asia/Seoul fr_MF                heure de la Corée
Asia/Seoul ms_ID                Waktu Korea
Asia/Seoul shi                  Korea Time
 */
