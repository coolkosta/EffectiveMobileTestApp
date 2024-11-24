package com.coolkosta.effectivemobiletestapp


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }
    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}

fun <T> Flow<T>.throttleLast(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }
    return this.conflate().transform {
        emit(it)
        delay(periodMillis)
    }
}





