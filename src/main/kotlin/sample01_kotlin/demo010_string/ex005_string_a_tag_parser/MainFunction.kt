
package sample01_kotlin.demo010_string.ex005_string_a_tag_parser

val sampleText = """
0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789
0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789
0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789\uD34D
""".trimIndent().trim()
//val sampleText = """0123 456 789""".trim()

fun main() {
    AnimationStyleStringMaker.getStringList(sampleText).forEach {
        println("--------------------------------------------------")
        println(it)
    }
}

// 0123<a style="font-size:1.1em>klkkk


// 0123<a style="font-size:1.1em
// 0123<a style="font-size:1.1em></a>   <--  처리 들어감
// 0123