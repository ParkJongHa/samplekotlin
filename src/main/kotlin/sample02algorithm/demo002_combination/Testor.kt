package sample02algorithm.demo002_combination

fun main() {
    val tempList = listOf("a", "b", "c", "d")
    val extractCount = 2

    println(
        "${tempList.size}C$extractCount = ${CombinationCounter(tempList.size, extractCount).get()}"
    ) // 4C2 = 6

    CombinationGenerator(tempList, extractCount).get().forEach {
        println( it ) // [a, b]
    }
}
/*
4C2 = 6
[a, b]
[a, c]
[a, d]
[b, c]
[b, d]
[c, d]
 */