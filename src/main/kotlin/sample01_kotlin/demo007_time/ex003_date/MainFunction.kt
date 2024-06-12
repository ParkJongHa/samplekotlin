package sample01_kotlin.demo007_time.ex003_date

import common.TimeUtil
import java.time.*
import java.time.format.DateTimeFormatter

fun main1() {
//    val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//        .parse("2023-06-10T21:35:11.282+00:00")

    val localDateTime = TimeUtil.getNow()
    val date = TimeUtil.getNowAsDate()

    println("localDateTime                       $localDateTime")
    println("date                                $date")
    println("TimeUtil.getAsDate(localDateTime)   " + TimeUtil.getAsDate(localDateTime))
    println("TimeUtil.getDateTimeAsFullPattern() " + TimeUtil.getDateTimeAsFullPattern())
    println("TimeUtil.getDateTimeAsNanoPattern() " + TimeUtil.getNow().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSSSSS")))
    println(localDateTime.format(TimeUtil.Companion.Pattern.formatterDB))

//    date.format(DateTimeFormatter.ofPattern(Pattern.FULL))

//    println( date )
//    println( Date() )
//    println( getLocalDateTimeFromUtcTime("2023-06-10T21:35:11.282+00:00") )

//    println(LocalDateTime.now(ZoneOffset.UTC))
//    println(TimeUtil.getDateTimeAsFullPattern())
//    println(Instant.now())

    localDateTimeNow()

    for ( i in 1 .. 5) {
        println("[$i]" + NumberCounter.getThreeDigitString() + " LocalDateTime.now(ZoneOffset.UTC)      " + LocalDateTime.now(ZoneOffset.UTC))
        println("[$i]" + NumberCounter.getThreeDigitString() + " TimeUtil.getDateTimeAsFullPattern()    " + TimeUtil.getDateTimeAsFullPattern())
        println("[$i]" + NumberCounter.getThreeDigitString() + " Instant.now                            " + Instant.now())
        println("[$i]" + NumberCounter.getThreeDigitString() + " localDateTimeNow                       " + localDateTimeNow())
        println("[$i]" + NumberCounter.getThreeDigitString() + " TimeUtil.getAsDate(localDateTimeNow()) " + TimeUtil.getAsDate(localDateTimeNow()))
    }

    println(
        TimeUtil.getTimeWithPattern(TimeUtil.getNow(), "yyyy-MM-dd HH:mm:ss.SSS001")
    )

    timeLocalDateTimeNano()
    timeLocalDateTimeNanoToDate()
    localDateTimeNow()
}

fun main() {
    for (i in 1.. 10000) {
        println("$i " + LocalDateTime.now(ZoneOffset.UTC).toString().substring(0, 23))
    }
    // 9600 2023-07-10T13:43:29.641
    // 9834 2023-07-10T13:43:29.643
    // 1ms 동안 234번 반복됨

    // 9307 2023-07-10T13:52:17.860
    // 9729 2023-07-10T13:52:17.862
    // 1ms 동안 422번 반복됨
}

fun timeLocalDateTimeNano() {
    val localDateTime = LocalDateTime.parse("2023-07-02 05:39:23.718001002", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"))

    println("timeLocalDateTimeNano > $localDateTime")
}

fun timeLocalDateTimeNanoToDate() {
    val localDateTime = LocalDateTime.parse("2023-07-02 05:39:23.718001002", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"))
    val date = TimeUtil.getAsDate(localDateTime)

    println("timeLocalDateTimeNanoToDate > $date")
}

fun localDateTimeNow(): LocalDateTime {
    return LocalDateTime.parse(
        LocalDateTime.now(ZoneOffset.UTC).toString().substring(0, 23) + NumberCounter.getSixDigitString(),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    ) // 2023-07-02T14:44:46.627999926
}





object NumberCounter {

    private var threeDigitNumber: Int = 0
    private var sixDigitNumber: Int = 0
    fun getThreeDigitNumber(withoutZero: Boolean = true): Int {
        if (threeDigitNumber < 0) {
            threeDigitNumber = 0
        }

        threeDigitNumber++

        if (999 < threeDigitNumber) {
            threeDigitNumber = 0
        }

        if (withoutZero) {
            if (threeDigitNumber == 0) threeDigitNumber = 1
        }

        return threeDigitNumber
    }
    fun getThreeDigitString(): String {
        return getThreeDigitNumber().toString().padStart(3, '0')
    }
    fun getSixDigitNumber(withoutZero: Boolean = true): Int {
        if (sixDigitNumber < 0) {
            sixDigitNumber = 0
        }

        sixDigitNumber++

        if (999999 < sixDigitNumber) {
            sixDigitNumber = 0
        }

        if (withoutZero) {
            if (sixDigitNumber == 0) sixDigitNumber = 1
        }

        return sixDigitNumber
    }
    fun getSixDigitString(): String {
        return getSixDigitNumber().toString().padStart(6, '0')
    }

}
