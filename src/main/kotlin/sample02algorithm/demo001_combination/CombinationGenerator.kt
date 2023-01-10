package sample02algorithm.demo001_combination

import java.util.*

class CombinationGenerator<T>(
    private val itemList: List<T>,
    private val extractCount: Int // r of (nCr)
) {

    private val resultListList: List<List<T>>
    private val resultArr: Array<Any>
    private val visitArr: Array<Boolean>

    init {
        this.resultListList = mutableListOf()
        this.resultArr = Array(extractCount) {}
        this.visitArr = Array(itemList.size) { false }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getCombinationResult(idx: Int, cnt: Int) {
        if (cnt == extractCount) {
            println("aaa")
            val tempResult = mutableListOf<T>()
            tempResult.addAll(resultArr.map {it as T})
            resultListList.plus(tempResult)
        } else {
            println("bbb")
            for (i in idx until itemList.size) {
                if (visitArr[i]) continue

                resultArr[cnt] = itemList[i] as Any
                visitArr[i] = true
                getCombinationResult(i, cnt+1)
                visitArr[i] = false
            }
        }
    }

    fun get(): List<List<T>> {
        getCombinationResult(0,0)
        println(resultListList)
        return resultListList
    }
}