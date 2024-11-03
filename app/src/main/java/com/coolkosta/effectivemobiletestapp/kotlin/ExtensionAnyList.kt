package com.coolkosta.effectivemobiletestapp.kotlin

fun List<Any>.getIntList(): List<Int>{
    val result = mutableListOf<Int>()
    for (i in this) {
        when (i) {
            is Int -> result.add(i)
        }
    }
    return result
}

object AnyList {
    val list: List<Any> = listOf(1, "two", "three", false, 5, 0.06, 1.7f, 8, '9', 10)
}