package common

import sample01_kotlin.demo007_time.ex003_date.NumberCounter
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*


class TimeUtil {

    companion object {

        const val OneWeekInMilliseconds = 7L * 86400000L

        class Pattern {
            companion object {
                const val YYYYMMDD = "yyyyMMdd"
                const val YYYYWW = "yyyyWw"
                const val YYYYMM = "yyyyMM"
                const val YYYY = "yyyy"

                const val FULL = "yyyyMMddHHmmssSSSSSS"
                const val YMDHMS = "yyyy-MM-dd HH:mm:ss"

                const val DB = "yyyy-MM-dd HH:mm:ss.SSSSSS"
                const val ISO_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"

                val formatterDB: DateTimeFormatter = DateTimeFormatter.ofPattern(DB)

                val formatterYmdhms: DateTimeFormatter = DateTimeFormatter.ofPattern(YMDHMS)
                val formatterYmdhms_asSimpleDateFormat: DateFormat = SimpleDateFormat(YMDHMS)
                val formatterIsoDateTime: DateTimeFormatter = DateTimeFormatter.ofPattern(ISO_DATE_TIME)

            }
        }

        fun getDateTimeAsFullPattern():String {
            return getNow().format(DateTimeFormatter.ofPattern(Pattern.FULL))
        }

        fun getTimeWithPattern(pattern: String): String {
            return getTimeWithPattern(getNow(), pattern)
        }

        fun getTimeWithPattern(localDateTime: LocalDateTime, pattern: String): String {
            if (Pattern.YYYYWW == pattern) {
                val nThWeek = localDateTime.get(WeekFields.of(Locale.US).weekOfWeekBasedYear())
                return "${localDateTime.year}${if (nThWeek<10) "0$nThWeek" else nThWeek}"// 202238 // 해당년에서시작한 n번째 주를 나타낸다

            } else if (Pattern.DB == pattern) {
                return localDateTime.format(Pattern.formatterDB) // 2023-07-03 14:35:15.995001

            }

            return localDateTime.format(DateTimeFormatter.ofPattern(pattern))
        }

        fun getNow():LocalDateTime {
            return LocalDateTime.parse(
                LocalDateTime.now(ZoneOffset.UTC).toString().substring(0, 23) + NumberCounter.getThreeDigitString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
            )  // 2023-07-03 14:05:18.789123 // micro 단위는 겹치기 않게 호출시마다 값을 변경한다
        }

        fun getNowAsDate(): Date {
            return getAsDate(getNow())
        }

        fun getAsDate(localDateTime:LocalDateTime): Date {
            return Timestamp.valueOf(localDateTime)
        }

        /**
         * utcTime 2023-06-10T21:35:11.282+00:00
         */
        fun getAsLocalDateTime(utcTime :String): LocalDateTime {
            return LocalDateTime.parse(utcTime, Pattern.formatterIsoDateTime)
        }

        /**
         * utcTime 2023-06-10T21:35:11.282+00:00
         */
        fun getAsDate(utcTime :String?): Date {
            return if (utcTime.isNullOrBlank()) {
                getNowAsDate()
            } else if (utcTime.contains("T") && utcTime.contains(" ")) {
                getAsDate(getAsLocalDateTime(utcTime.replaceFirst(" ", "+")))
            } else {
                getAsDate(getAsLocalDateTime(utcTime))
            }
        }

        fun getNowYear(): Int{
            return getNow().year
        }

        fun getDatePlusMinusDay(day: Long): LocalDateTime {
            if (0L == day) return getNow()
            return getNow().plusDays(day)
        }

        fun getDatePlusMinusWeek(week: Long): LocalDateTime {
            if (0L == week) return getNow()
            return getNow().plusWeeks(week)
        }

        fun getDatePlusMinusMonth(month: Long): LocalDateTime {
            if (0L == month) return getNow()
            return getNow().plusMonths(month)
        }

        fun getDatePlusMinusYear(year: Long): LocalDateTime {
            if (0L == year) return getNow()
            return getNow().plusYears(year)
        }

        fun toLocalDateTime(date: Date): LocalDateTime {
            return LocalDateTime.ofEpochSecond(date.time/1000, 0,ZoneOffset.UTC)
        }

        /**
         * dbFormatTime = "yyyy-MM-dd HH:mm:ss.SSS000" 포멧
         */
        fun getLocalDateTime(dbFormatTime: String): LocalDateTime {
            return LocalDateTime
                .parse(dbFormatTime,Pattern.formatterDB)
        }

        /**
         *            yyyy-MM-dd HH:mm:ss
         * utcRawTime 2022-07-07 07:23:30
         */
        fun getLocalDateTimeFromUtcTime(utcRawTime: String?): LocalDateTime? {
            if (utcRawTime.isNullOrBlank() || 10>utcRawTime.length) return null

            var time = if (19 <= utcRawTime.length) utcRawTime.substring(0, 19)
            else utcRawTime.padEnd(19, '0')

            time = time.substring(0, 10) + " " + time.substring(11)
            time = time.substring(0, 13) + ":" + time.substring(14)
            time = time.substring(0, 16) + ":" + time.substring(17)

            val offsetDateTime: OffsetDateTime = OffsetDateTime.now(ZoneId.systemDefault())
            val utcLocalDateTime = LocalDateTime.parse(time, Pattern.formatterYmdhms)

            return LocalDateTime.ofEpochSecond(utcLocalDateTime.toEpochSecond(ZoneOffset.UTC), 0, offsetDateTime.offset)
        }

    }

}