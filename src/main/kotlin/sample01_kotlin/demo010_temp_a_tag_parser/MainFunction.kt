
package sample01_kotlin.demo010_temp_a_tag_parser

val sampleText = """
    0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789
    0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789
    0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789
""".trimIndent().trim()
//val sampleText = """0123 456 789""".trim()

fun main() {
    AnimationStyleStringMaker.getStringList(sampleText).forEach {
        println("--------------------------------------------------")
        println(it)
    }
}