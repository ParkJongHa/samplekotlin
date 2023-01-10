package sample02algorithm.demo001_combination

fun main() {
    val tempList = listOf("a", "b", "c", "d")

    CombinationGenerator(tempList, 2).get().forEach {
        println( it )
        println( it.joinToString { "," } )
    }
}