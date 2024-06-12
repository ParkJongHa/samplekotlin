package sample01_kotlin.demo010_string.ex001_string_length

fun main() {
    var str: String = "abc"
    println( "$str length = ${str.length}" )

    str = "일이삼"
    println( "$str length = ${str.length}" )

    str = "ａｂｃ"
    println( "$str length = ${str.length}" )

    str = "\uD84E\uDD9D"
    println( "$str length = ${str.length}" )

    str = "✌(‘ω’)✌".substring(0, 1)
    println( "$str length = ${str.length}" )

    str = "嬉".substring(0, 1)
    println( "$str length = ${str.length}" )

    str = "\uD84E\uDD9D".substring(0, 1)
    println( "$str length = ${str.length}" )

    str = "\uD83D\uDE33"
    println( "$str length = ${str.length}" )

    str = "\uD83D\uDE33".substring(0, 1)
    println( "$str length = ${str.length}" )

    str = "\uD83D\uDE33".substring(0, 1)
    println("? is same $str ${str.equals("?")}")

    str = "\uD83D\uDE33"
    println(str.contains("\\u"))

    str = "\uD83D\uDE33"
    println(str.contains("\uDE33"))

}
