package sample02_algorithm.demo002_combination

class CombinationCounter {

    constructor(
        itemList: List<Any>,
        extractCount: Int
    ): this(itemList.size, extractCount)

    constructor(
        itemSize: Int,
        extractCount: Int
    ) {
        this.count = combination(itemSize, extractCount)
    }

    private val count: Int

    private fun combination(n: Int, r: Int): Int {
        if (n==r || r==0) return 1
        return combination(n-1, r-1) + combination(n-1, r)
    }

    fun get(): Int {
        return count
    }
}