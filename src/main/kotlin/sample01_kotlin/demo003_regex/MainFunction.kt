package sample01_kotlin.demo003_regex

fun main1() {
    val specialCharactors = """[×÷€£¥₩°•○●□■♤♡◇♧☆▪︎¤《》¡¿{}\[\]\\/?.,;:|()*~`!^\-_+┼<>@#${'$'}%&'"=]"""
    val specialCharactorsRegex = Regex(specialCharactors)
    val nickname = "ABC:)짱"

    for (ch in nickname.indices) {
        println("$ch ${nickname[ch]} isSpecialChar?:${specialCharactors.contains(nickname[ch])}")
    }

    println( "nickname.contains(specialCharactors) : " + nickname.contains(specialCharactors) )
    println( "nickname.contains(specialCharactorsRegex) : " + nickname.contains(specialCharactorsRegex) )
    println( nickname.replace(specialCharactorsRegex, "") )
}
fun main() {
    val text = """Hello <a style="color:#FF9900;">world</a>. <a style="color:#0099FF;">I'm</a> the king \n<a style="font-weight:bold;" > I can do it</a>"""
    val aTagRegex = Regex("<a.*?>.*?</a>")
    aTagRegex.findAll(text).iterator().forEach { matchResult -> println(matchResult.value) }//groupValues?.forEach { println(it) }

//    text.matches(aTagRegex)
//    text.split(aTagRegex).forEach { println(it) }

    val fontSize = "8.3em"

//    fontSize.matches(Regex("em"))

//    println(fontSize.matches(Regex("em").))

    var matchResult: MatchResult? = Regex("[Pp][Xx]|[Ee][Mm]|%").find(fontSize)
    println(matchResult?.value)

    matchResult= Regex("[0-9.]*").find(fontSize)
    println(matchResult?.value)


}