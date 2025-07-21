package com.romakost.network

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.abs
import kotlin.math.min

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testl1() {
        shortestToChar("loveleetcode", 'e')
    }

    fun shortestToChar(s: String, c: Char): IntArray {
        if (s.length == 1) return intArrayOf(0)

        val res = mutableListOf<Int>()
        var l = 0
        for (i in 0..s.lastIndex) {
            if (s[i] == c) {
                val ll = (0..< i-l).toList().reversed()
                res.addAll(ll)
                l = i
            }
        }

        return res.toIntArray()
    }
}
