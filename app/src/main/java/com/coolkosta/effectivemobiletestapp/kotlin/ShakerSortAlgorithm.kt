package com.coolkosta.effectivemobiletestapp.kotlin

object ShakerSortAlgorithm {
    fun sort(list: List<Int?>): List<Int?> {
        if (list.all { it == null }) return list

        val nullableList = list.filter { it == null }
        val nonNullList = list.filterNotNull().toMutableList()

        fun swap(i: Int, j: Int) {
            val temp = nonNullList[i]
            nonNullList[i] = nonNullList[j]
            nonNullList[j] = temp
        }
        do {
            var swaped = false
            for (i in 0 until nonNullList.size - 1) {
                if (nonNullList[i] > nonNullList[i + 1]) {
                    swap(i, i + 1)
                    swaped = true
                }
            }
            if (!swaped) break
            swaped = false
            for (i in nonNullList.size - 2 downTo 0) {
                if (nonNullList[i] > nonNullList[i + 1]) {
                    swap(i, i + 1)
                    swaped = true
                }
            }
        } while (swaped)

        return nonNullList + nullableList
    }
}

object SomeListForShakerAlgorithm {
    val firstExample = listOf(null, null, null, null, null, null)
    val secondExample = listOf(1, 3, 2, 4, 8, 6, 0, 5, 7, 10, 9)
    val thirdExample = listOf(null, 5, null, null, 1, 4, 2, null, 8, 3, 0, null, 6, 10, 9, 7)
}

