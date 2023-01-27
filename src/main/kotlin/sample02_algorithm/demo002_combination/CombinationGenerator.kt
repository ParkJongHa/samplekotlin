package sample02_algorithm.demo002_combination

import java.util.*

class CombinationGenerator<T>(
    private val itemList: List<T>,
    private val extractCount: Int // r of (nCr)
) {

    private var resultListList: List<List<T>>
    private val resultArr: Array<Any>
    private val visitArr: Array<Boolean>

    init {
        this.resultListList = mutableListOf()
        this.resultArr = Array(extractCount) {}
        this.visitArr = Array(itemList.size) { false }

        getCombinationResult(0,0)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getCombinationResult(idx: Int, cnt: Int) {
        if (cnt == extractCount) {
            val tempResult = mutableListOf<T>()
            tempResult.addAll(resultArr.map {it as T})
            resultListList = resultListList.plusElement(tempResult)
        } else {
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
        return resultListList
    }
}