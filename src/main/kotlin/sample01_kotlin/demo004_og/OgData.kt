package sample01_kotlin.demo004_og

import com.google.gson.Gson

// inline class는 Primitive type을 기준으로 사용할 수 있고, inline class 코드 내에는 프로퍼티와 함수 정의하는 게 가능하다.
inline class OgData(private val ogExtractorTags: Map<String, List<String>>) {

    val title: String?
        get() = allTitleList().firstOrNull()

    val image: String?
        get() = allImageList().firstOrNull()

    val url: String?
        get() = allUrlList().firstOrNull()

    val description: String?
        get() = allDescriptionList().firstOrNull()

    fun allTitleList(): List<String> {
        return ogExtractorTags["title"] ?: emptyList()
    }

    fun allImageList(): List<String> {
        return extractImageUrl()
    }

    fun allDescriptionList(): List<String> {
        return ogExtractorTags["description"] ?: emptyList()
    }

    fun allUrlList(): List<String> {
        return ogExtractorTags["url"] ?: emptyList()
    }

    fun getProperty(name: String): String? {
        return getPropertyList(name).firstOrNull()
    }

    fun getPropertyList(name: String): List<String> {
        return ogExtractorTags[name]
            ?: emptyList()
    }

    private fun extractImageUrl(): List<String> {
        return ogExtractorTags["image"]
            ?: ogExtractorTags["image:url"]
            ?: ogExtractorTags["image:secure_url"]
            ?: emptyList()
    }

    override fun toString(): String = buildString {
        ogExtractorTags.forEach { (property, content) -> appendLine("$property: $content") }
    }

    fun toJsonString(): String = Gson().toJson(ogExtractorTags).toString()
}