package com.coolkosta.effectivemobiletestapp

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CoroutineKtTest {

    @Test
    fun throttleFirstWithCompleted() = runBlocking {
        val test = mutableListOf<Int>()
        (1..10)
            .asFlow()
            .onEach { delay(100) }
            .throttleFirst(200)
            .collect {
                test.add(it)
            }

        assertEquals(listOf(1, 3, 5, 7, 9), test)

    }

    @Test
    fun throttleLastWithCompleted() = runTest {
        val test = mutableListOf<Int>()
        (1..10)
            .asFlow()
            .onEach { delay(100) }
            .throttleLast(300)
            .collect {
                test.add(it)
            }
        assertEquals(listOf(1, 3, 6, 9, 10), test)

    }
}