package sample01_kotlin.demo010_string.ex005_string_a_tag_parser

import java.util.regex.Pattern


object AnimationStyleStringMaker {

    private const val aTagStyleHeader = "<a style="
    private val invalidSet = setOf("<", "<a", "<a ", "<a s", "<a s", "<a st", "<a sty", "<a styl", "<a style", "<a style=")

    private fun getCharsCount(text: String): Int {
        val pattern = Pattern.compile("<[^>]*>") // HTML 태그 제거 위한 정규식 패턴
        val matcher = pattern.matcher(text) // Matcher 객체 생성
        val cleanedText = matcher.replaceAll("") // Matcher 사용 하여 HTML 태그를 제거
        return cleanedText.length // 제거된 텍스트 글자수
    }

    private fun getMakeEndTag(tString: String): String {
        return if (tString.endsWith("<")) "/a>"
        else if (tString.endsWith("</")) "a>"
        else if (tString.endsWith("</a")) ">"
        else "</a>"
    }

    private var tString: String = ""
    private var tOnlyCharsCount: Int = Int.MIN_VALUE
    private var tLastStartIndex: Int = Int.MIN_VALUE
    private var tLastStartEndIndex: Int = Int.MIN_VALUE
    private var tLastEndIndex: Int = Int.MIN_VALUE
    private var lastAStyleIndex: Int = Int.MIN_VALUE

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

        val animationStyleStringList = mutableListOf<String>()

        for(i: Int in unitCount..originText.length step(unitCount)) {
            tString = originText.subSequence(0, i).toString()

            if (invalidSet.contains(tString)) {
                continue
            }
            if (CharCategory.SURROGATE == tString[tString.length-1].category) { // "쭉쭉\uD83C\uDF81" > "쭉쭉\uD83C" 됬을시 마지막 char 체크해 � 문자 존재 확인
                println("0) continue CharCategory.SURROGATE")
                continue
            }

            tOnlyCharsCount = getCharsCount(tString)

            if (0 == tOnlyCharsCount) {
                continue
            }
            if (onlyCharsCount < tOnlyCharsCount) {
                continue
            }
            if (tString.contains(aTagStyleHeader) && !tString.contains(">")) {
                continue
            }
            if (tString.contains(aTagStyleHeader) && tOnlyCharsCount == tString.length) {
                continue
            }

            tLastStartIndex = tString.lastIndexOf("<")
            tLastStartEndIndex = tString.lastIndexOf(">")
            tLastEndIndex = tString.lastIndexOf("</a>")

            if (tLastStartIndex < 0 && tLastEndIndex < 0) {
                animationStyleStringList.add(tString)

            } else if (0 < tLastEndIndex && tLastStartIndex == tLastEndIndex) {
                animationStyleStringList.add(tString)

            } else if (0 < tLastStartIndex && 0 < tLastStartEndIndex) { // tag 안닫힘
                println("1) tString before : $tString")

                if (tLastEndIndex in 1 until tLastStartIndex && tLastEndIndex < tLastStartEndIndex) {
                    lastAStyleIndex = tString.lastIndexOf(aTagStyleHeader)
                    tString =
                        if (lastAStyleIndex in 1 until tLastStartEndIndex && tLastEndIndex < lastAStyleIndex) {
                            tString + getMakeEndTag(tString)
                        } else {
                            tString.subSequence(0, tLastStartIndex).toString()
                        }
                } else {
                    tString =
                        if (tLastEndIndex < tLastStartEndIndex) {
                            tString + getMakeEndTag(tString)
                        } else { // <a style=color:#2 // 이렇게 끝날수 있다.
                            tString + tString.subSequence(0, tLastStartIndex).toString()// + "</a>"
                        }
                }
                println("   tString  after : $tString")
                animationStyleStringList.add(tString)

            } else {
                println("2) tString before : $tString")
                if (0 < tLastStartIndex) {
                    tString = tString.subSequence(0, tLastStartIndex).toString()
                }
                if (0 <= tLastStartIndex
                    && 0 < tLastStartEndIndex
                    && 0 > tLastEndIndex
                ) { // a tag 로 시작후 제대로 닫히지 않음
                    tString += getMakeEndTag(tString)
                }
                println("   tString  after : $tString")
                animationStyleStringList.add(tString)
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