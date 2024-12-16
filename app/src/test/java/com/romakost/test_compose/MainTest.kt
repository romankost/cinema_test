package com.romakost.test_compose

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.yield
import org.junit.Test
import java.util.ArrayList

class MainTest {

    @Test
    fun test() {
        letterCombinations("234")
    }

    private val Char.letters
        get() = when (this) {
            '2' -> listOf('a', 'b', 'c')
            '3' -> listOf('d', 'e', 'f')
            '4' -> listOf('g', 'h', 'i')
            '5' -> listOf('j', 'k', 'l')
            '6' -> listOf('m', 'n', 'o')
            '7' -> listOf('p', 'q', 'r', 's')
            '8' -> listOf('t', 'u', 'v')
            '9' -> listOf('w', 'x', 'y', 'z')
            else -> listOf()
        }

    fun letterCombinations(digits: String): List<String> {
        return digits.fold(listOf("")) { acc, c ->
            c.letters.flatMap { l -> acc.map { it + l } }
        } ?: emptyList()
    }

}

