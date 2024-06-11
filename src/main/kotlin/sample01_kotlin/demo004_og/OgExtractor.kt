package sample01_kotlin.demo004_og

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.net.URL

object OgExtractor {

    private val ogExtractorCache: OgExtractorCache<URL, OgData> = OgExtractorCache()

    fun clearCache() {ogExtractorCache.clear()}

    suspend fun parse(url: URL): OgData = withContext(Dispatchers.IO) {
        ogExtractorCache.computeIfAbsent(url) {
            extractTags( Jsoup.connect(url.toString()).get() )
        }
    }

    private suspend fun extractTags(document: Document): OgData = withContext(Dispatchers.Default) {
        val tagMapList: Map<String, List<String>> = document
            .head()
            .getElementsByTag("meta")
            .filter { element -> element.isOpenGraphTag()}
            .groupBy {it.propertyWithoutOgTag()}
            .mapValues { elementMap -> elementMap.value.map { element -> element.attr("content")}}

        OgData(tagMapList)
    }

    private fun Element.isOpenGraphTag(): Boolean {
        return hasAttr("property") && attr("property").startsWith("og:")
    }
    private fun Element.propertyWithoutOgTag(): String {
        return attr("property").substringAfter(":")
    }

    suspend fun URL.getOgData(): OgData {
        return parse(this)
    }
}