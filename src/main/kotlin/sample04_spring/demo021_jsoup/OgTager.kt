package sample04_spring.demo021_jsoup

import org.jsoup.Jsoup

class OgTag {

    fun getTitle(url: String): String? {
        return try {
            val doc = Jsoup.connect(url).get() // Document
            val ele = doc.select("meta[property=og:title]")

            if (0<ele.size) {
                return ele[0].attr("content").trim()
            }
            return ""
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}