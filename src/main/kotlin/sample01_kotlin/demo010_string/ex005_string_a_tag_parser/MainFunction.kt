
package sample01_kotlin.demo010_string.ex005_string_a_tag_parser

val sampleText01 = """
0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789
0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789
0123<a style="font-size:1.1em;font-weight:ligher;color:#FF9900FF;background-color:#000000FF;">abcdefghi</a>789\uD34D
""".trimIndent().trim()

val sampleText02 = """0123 456 789""".trim()

val sampleText03 = """
<a style=font-weight:bold;color:#ff0000; >높아지는 생활비</a>에 
<a style=color:#242995;font-weight:bold; >줄어가는 내 통장 잔고</a>, 
<a style=font-weight:bold; >이제 걱정 그만!</a>    
""".trimIndent()

val sampleText04 = """이렇게 대화를 할 때마다 위 <a style=font-weight:bold;color:#ff9c00; >게이지바가 쭉쭉 올라, 🎁 선물을 받으실 수</a> 있어요!""".trimIndent()

fun main() {
    AnimationStyleStringMaker.getStringList(sampleText04).forEach {
        println("--------------------------------------------------")
        println(it)
    }
}

// 0123<a style="font-size:1.1em>klkkk


// 0123<a style="font-size:1.1em
// 0123<a style="font-size:1.1em></a>   <--  처리 들어감
// 0123