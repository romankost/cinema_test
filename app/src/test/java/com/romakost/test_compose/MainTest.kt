package com.romakost.test_compose

import android.util.Log
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import org.junit.Test
import java.util.ArrayList

class MainTest {
    lateinit var q: String

    @Test
    fun test() {
       test1()
    }

    fun test1() {
        runBlocking {
            launch {
                delay(1000L)
                println("Coroutine 1 finished")
            }
            println("After launching coroutine")
            delay(2000L)
            println("Main finished")
        }
    }

    fun coroutineTest() {
        val excHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            print("Exception")
        }

        val scope = CoroutineScope(Job() + excHandler + CoroutineName("test Coroutine")).launch {
            withContext(Dispatchers.Main){

            }
        }

        scope.cancel()
    }

    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        val res = mutableSetOf<List<Int>>(listOf())
        val l = nums.toMutableList()

        if (nums.size == 1)  {
            res.add(nums.toList())
            return res.toList()
        }


        for(i  in 0..< nums.lastIndex) {
            for(k in  1 .. nums.lastIndex) {
                val q = l.subList(k, i + 1)
                res.add(q)
            }
        }

        return res.toList()
    }
}

