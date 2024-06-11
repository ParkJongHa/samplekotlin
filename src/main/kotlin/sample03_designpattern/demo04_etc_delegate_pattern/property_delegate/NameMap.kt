package sample03_designpattern.demo04_etc_delegate_pattern.property_delegate

val nameMap = mutableMapOf(
    "first" to "1st",
    "second" to "2st",
    "third" to "3st"
)

class NameBy {
    var first by nameMap
    var second by nameMap
    var third by nameMap
    fun print() {
        println( listOf(first, second, third) )
    }
}

fun main() {
    val nameBy = NameBy()
    nameBy.print()
}