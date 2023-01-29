package sample01_kotlin.demo001_dft.ex005_string_special_char

fun main1() {
    val str = "abc!@#123일이삼一二三ａｂｃ\uD83D\uDE33しごと"

    var regex = Regex("[^\uAC00-\uD7A30-9a-zA-Z\\s]")
    println(str.replace(regex, ""))

    regex = Regex("""[\{\}\[\]\\/?.,;:|\)*~`!^\-_+┼<>@\#${'$'}%&\'\"\\(\=]""")
    println(str.replace(regex, ""))
}
