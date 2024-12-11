package sample01_kotlin.demo013_file.ex001_read

import data.categoryList
import data.countryList
import org.jsoup.Jsoup
import sample01_kotlin.demo013_file.ex002_write.fileWrite
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.random.Random

fun main() {
//    domainExtract()
//    combination()
    getDomainSet()
}

fun domainExtract() {
    val file = File("C:\\Users\\name\\a.html")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
//    reader.lines().forEach { println(it) }

    var splittedLine: List<String>
    var splittedRaw: List<String>
    val domainList = mutableSetOf<String>()

    reader.lines().forEach { aLine ->
        splittedLine = aLine.split("data-cl=\"true\">")

        if (1 < splittedLine.size) {
            splittedLine.forEach{ aRaw ->
                splittedRaw = aRaw.split("</a></div></td>")
                if (1 < splittedRaw.size) {
                    domainList.add( splittedRaw[0] )
                }
            }
        }
    }

//    domainList.forEach { println(it) }
}

fun combination() {
    var totalCount = 0
    countryList.forEach { categoryList.forEach { totalCount++ } }

    var index = 0
    countryList.forEach { aCountry ->
        categoryList.forEach { aCategory ->
            index++
            println("${"$index".padStart(totalCount.toString().length, '0')} $aCountry/$aCategory")
        }
    }
}

fun getDomainSet() {

    countryList.forEach { aCountry ->
        categoryList.filter { it != "adult" }.forEach { aCategory ->
            println("$aCountry $aCategory start")

            val domainSet = mutableSetOf<String>()
            val start = System.currentTimeMillis()

            urlExtract(aCountry, aCategory, domainSet)
            fileWrite(domainSet.toList())

            println("$aCountry $aCategory delay (${System.currentTimeMillis() - start}ms)")
            Thread.sleep(1500L + Random.nextLong(1500))
        }
    }
}

fun urlExtract(countryCode: String, category: String, domainSet: MutableSet<String>) {
    try {
        val document = Jsoup.connect("https://ko.semrush.com/trending-websites/$countryCode/$category").get()

        val section = document.childNodes()[4]
            .childNodes()[3]
            .childNodes()[3]
            .childNodes()[15]
            .childNodes()[1]
            .childNodes()[5]
            .childNodes()[0]
            .childNodes()[2]
            .childNodes()[0]
            .childNodes()[1]

        val tbody = section
            .childNodes()[1]
            .childNodes()[0]
            .childNodes()[0]
            .childNodes()[0]
            .childNodes()[1]

        tbody.childNodes().forEach { tr ->
            try {
                val node = tr.childNodes()[1]
                    .childNodes()[0]
                    .childNodes()[1]
                    .childNodes()[0]

                domainSet.add( node.outerHtml() )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
