package com.coolkosta.effectivemobiletestapp.kotlin

fun List<Any>.getInt(): Int? {
    for (i in this) {
        when (i) {
            is Int -> return i
        }
    }
    return null
}

object AnyList {
    val list: List<Any> = listOf( "two", false, 0.06, 1.7f, '9', 56L, arrayOf(5,6,7), 5 )
}