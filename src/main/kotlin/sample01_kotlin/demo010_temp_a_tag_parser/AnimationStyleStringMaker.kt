package sample01_kotlin.demo010_temp_a_tag_parser

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
        animationDuration: Int = 3000,
        animationUnitTime: Int = 1000,
    ): List<String> {
        val onlyCharsCount = getCharsCount( originText )
        val changeCount = animationDuration / animationUnitTime // 100 백번의 변경을 한다는 뜻
        val unitCount = (onlyCharsCount / changeCount).let {
            if (it < 1) 1 else it
        }

        var tString: String
        var tOnlyCharsCount: Int
        var tLastStartIndex: Int
        var tLastStartEndIndex: Int
        var tLastEndIndex: Int
        val animationStyleStringList = mutableListOf<String>()

        for(i: Int in 1..originText.length step(unitCount)) {
            tString = originText.substring(0 until i)
            tOnlyCharsCount = getCharsCount(tString)

            if (onlyCharsCount < tOnlyCharsCount) {
                continue
            }

            tLastStartIndex = tString.lastIndexOf("<")
            tLastStartEndIndex = tString.lastIndexOf(">")
            tLastEndIndex = tString.lastIndexOf("</a>")

            if (tLastStartIndex < 0 && tLastEndIndex < 0) {
                animationStyleStringList.add(tString)
            } else if (0 < tLastEndIndex && tLastStartIndex == tLastEndIndex) {
                animationStyleStringList.add(tString)
            } else {
                if (0 < tLastStartIndex
                    && 0 < tLastStartEndIndex
                    && tLastEndIndex < 0
                ) { // tag 안닫힘
                    if (tString.endsWith("<")) {
                        tString += "/a>"
                    } else if (tString.endsWith("</")) {
                        tString += "a>"
                    } else if (tString.endsWith("</a")) {
                        tString += ">"
                    } else {
                        tString += "</a>"
                    }
                    animationStyleStringList.add(tString)
                } else {
                    if (0 < tLastStartIndex) {
                        tString = tString.substring(0, tLastStartIndex)
                    }
                    animationStyleStringList.add(tString)
                }
            }
        }

        animationStyleStringList.add(originText)
        return animationStyleStringList
    }
}