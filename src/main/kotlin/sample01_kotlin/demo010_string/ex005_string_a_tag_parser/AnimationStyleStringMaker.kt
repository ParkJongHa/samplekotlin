package sample01_kotlin.demo010_string.ex005_string_a_tag_parser

import java.util.regex.Pattern

object AnimationStyleStringMaker {

    fun getCharsCount(text: String): Int {
        val pattern = Pattern.compile("<[^>]*>") // HTML 태그를 제거하기 위한 정규식 패턴
        val matcher = pattern.matcher(text) // Matcher 객체 생성
        val cleanedText = matcher.replaceAll("") // Matcher를 사용하여 HTML 태그를 제거
        return cleanedText.length // 제거된 텍스트의 글자 수 계산
    }

    fun getStringList(
        originText: String,
        animationDuration: Long = 1000,
        animationUnitTime: Long = 50,
        isRemoveDupl: Boolean = true,
    ): List<String> {
        val onlyCharsCount = getCharsCount( originText )
        val changeCount = ((animationDuration / animationUnitTime).toInt()) // 100 백번의 변경을 한다는 뜻
            .let {if(it < 1) 1 else it}
        val unitCount = (onlyCharsCount / changeCount)
            .let {if (it < 1) 1 else it}

        var tString: String
        var tOnlyCharsCount: Int
        var tLastStartIndex: Int
        var tLastStartEndIndex: Int
        var tLastEndIndex: Int
        val animationStyleStringList = mutableListOf<String>()

        for(i: Int in unitCount..originText.length step(unitCount)) {
            tString = originText.substring(0 until i)

            if (tString == "<"
            || tString == "<a"
            || tString == "<a "
            || tString == "<a s"
            || tString == "<a st"
            || tString == "<a sty"
            || tString == "<a styl"
            || tString == "<a style"
            || tString == "<a style="
            ) {
                continue
            }

            tOnlyCharsCount = getCharsCount(tString)

            if (0 == tOnlyCharsCount) {
                continue
            }
            if (onlyCharsCount < tOnlyCharsCount) {
                continue
            }
            if (tString.contains("<a style=") && !tString.contains(">")) {
                continue
            }
            if (tString.contains("<a style=") && tOnlyCharsCount == tString.length) {
                continue
            }

            tLastStartIndex = tString.lastIndexOf("<")
            tLastStartEndIndex = tString.lastIndexOf(">")
            tLastEndIndex = tString.lastIndexOf("</a>")

            if (tLastStartIndex < 0 && tLastEndIndex < 0) {
                println("1) tLastStartIndex:$tLastStartIndex  tLastStartEndIndex:$tLastStartEndIndex tLastEndIndex:$tLastEndIndex\n$tString\n\n")
                animationStyleStringList.add(tString)
            } else if (0 < tLastEndIndex && tLastStartIndex == tLastEndIndex) {
                println("2) tLastStartIndex:$tLastStartIndex  tLastStartEndIndex:$tLastStartEndIndex tLastEndIndex:$tLastEndIndex\n$tString\n\n")
                animationStyleStringList.add(tString)
            } else {
                if (0 < tLastStartIndex
                 && 0 < tLastStartEndIndex
                ) { // tag 안닫힘
                    println("3) tLastStartIndex:$tLastStartIndex  tLastStartEndIndex:$tLastStartEndIndex tLastEndIndex:$tLastEndIndex" +
                            " before\n$tString\n\n")
                    tString +=
                        if (tLastEndIndex < tLastStartEndIndex) {
                            if (tString.endsWith("<")) "/a>"
                            else if (tString.endsWith("</")) "a>"
                            else if (tString.endsWith("</a")) ">"
                            else "</a>"
                        } else {// <a style=color:#2 // 이렇게 끝날수 있다.
                            tString.substring(0, tLastStartIndex)// + "</a>"
                        }
                    println("3) tLastStartIndex:$tLastStartIndex  tLastStartEndIndex:$tLastStartEndIndex tLastEndIndex:$tLastEndIndex" +
                            " after\n$tString\n\n")
                    animationStyleStringList.add(tString)
                } else {
                    if (0 < tLastStartIndex) {
                        tString = tString.substring(0, tLastStartIndex)
                    }
                    if (0 <= tLastStartIndex
                    && 0 < tLastStartEndIndex
//                    && tLastStartIndex < tLastStartEndIndex
                    && 0 > tLastEndIndex
                    ) { // a tag 로 시작하고 제대로 닫히지 않음
                        tString +=
                            if (tString.endsWith("<")) "/a>"
                            else if (tString.endsWith("</")) "a>"
                            else if (tString.endsWith("</a")) ">"
                            else "</a>"
                    }
                    println("4) tLastStartIndex:$tLastStartIndex  tLastStartEndIndex:$tLastStartEndIndex tLastEndIndex:$tLastEndIndex\n$tString\n\n")
                    animationStyleStringList.add(tString)
                }
            }
        }

        animationStyleStringList.add(originText)

        return if (isRemoveDupl) {
            val removedDuplStringList = mutableListOf<String>()
            animationStyleStringList.forEach{ aString ->
                if (removedDuplStringList.isEmpty()) {
                    removedDuplStringList.add(aString)
                } else {
                    if (removedDuplStringList.last() == aString) {
                        // 중복
                    } else {
                        removedDuplStringList.add(aString)
                    }
                }
            }
            removedDuplStringList
        } else {
            animationStyleStringList
        }
    }
}