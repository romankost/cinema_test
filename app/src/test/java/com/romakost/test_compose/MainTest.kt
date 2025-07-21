package com.romakost.test_compose

import android.text.TextUtils.substring
import android.util.Log
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.util.fastForEachIndexed
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import org.junit.Test
import java.math.BigInteger
import java.util.ArrayList
import java.util.Locale
import java.util.PriorityQueue
import kotlin.Double
import kotlin.collections.flatten
import kotlin.coroutines.CoroutineContext
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

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

    @Test
    fun t() {
        shortestCompletingWord("1s3 PSt", arrayOf("step","steps","stripe","stepple"))
    }

    fun shortestCompletingWord(licensePlate: String, words: Array<String>): String {
        val l = licensePlate.filterNot{ it.isDigit() || it.isWhitespace()}.toMutableList()
        var res = ""
        var ll = Int.MAX_VALUE
        words.forEach {
            if ( isContain(it.toList(), l)) {
                if (it.length < ll) {
                    res = it
                    ll = it.length
                }
            }
        }
        return res
    }

    fun isContain(c1: List<Char>, c2: MutableList<Char>): Boolean {
        for (i in c1) {
            val d = c2.indexOfFirst { it.equals(i, ignoreCase = true) }
            if (d >= 0) {
                c2.removeAt(d)
            }

            if (c2.isEmpty()) return true
        }
        return false
    }

    @Test
    fun ttt() {
        numberOfLines(intArrayOf(4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10) ,"bbbcccdddaaa")
    }


    fun largestTriangleArea(points: Array<IntArray>): Double {
        var m = 0.0
        for (i in 0 until points.size) {
            for (j in i + 1 until points.size) {
                for (k in j + 1 until points.size) {
                    val ar = trAria(points[i], points[j], points[k])
                    m = max(m, ar)
                }
            }
        }

        return 0.0
    }

    private fun trAria(a: IntArray, b: IntArray, c: IntArray ): Double {
        if ((a[0] == b[0] && a[0] == c[0])|| (a[1] == b[1] && a[1] == c[1])) return 0.0
        val p = dist(a, b) + dist(a, c) + dist(c, b) * 0.5
        val ar = sqrt(p * (p - dist(a, b)) * (p - dist(a, c)) * (p - dist(c, b)) )
        return if (ar.isNaN()) 0.0 else ar
    }

    private fun  dist(a: IntArray, b: IntArray): Double {
        var dx = abs(a[0] - b[0])
        var dy = abs(a[1] - b[1])
        return sqrt((dx*dx + dy*dy).toDouble())
    }

    fun numberOfLines(widths: IntArray, s: String): IntArray {
        var f = 0
        var cur = 0

        s.map {
            widths[it - 'a']
        }.forEach {
            if (cur + it > 100) {
                f++
                cur = it
            } else {
                cur += it
            }
        }
        return intArrayOf(f, cur)
    }

    fun mergeAlternately(word1: String, word2: String): String {
        val sb = StringBuilder()

        for(i in 0..word1.lastIndex) {

            if (word2.lastIndex < i) {
                sb.append(word1.substring(i))
                return sb.toString()
            } else {
                sb.append(word1[i])
            }

            if (i == word1.lastIndex && word1.lastIndex < word2.lastIndex) {
                sb.append(word2.substring(i))
            } else {
                sb.append(word2[i])
            }

        }
        return sb.toString()
    }

    @Test
    fun testTaskOlga() {
        val res = scheduling(
            mapOf(
                "Abby" to intArrayOf(10, 100),
                "Ben" to intArrayOf(50, 70),
                "Carla" to intArrayOf(60, 120),
                "David" to intArrayOf(150, 300)
            )
        )
        println("Start | End  | Names")
        println("-------------------------")
        res.forEach {
            println("${it.key[0]} | ${it.key[0]} | ${it.value.joinToString(",")} ")
        }
    }

//    Name   | Start | End
//    -------|-------|-----
//    Abby   | 10    | 100
//    Ben    | 50    | 70
//    Carla  | 60    | 120
//    David  | 150   | 300
//
//    Start | End  | Names
//    ------|------|-------------------
//    10    | 50   | Abby
//    50    | 60   | Abby, Ben
//    60    | 70   | Abby, Ben, Carla
//    70    | 100  | Abby, Carla
//    100   | 120  | Carla
//    150   | 300  | David

    fun scheduling(s: Map<String, IntArray>): Map<List<Int>, Set<String>> {
        return s.values
            .flatMap { it.toList() }
            .sorted()
            .windowed(2,1)
            .associate { it to s.filter { i -> maxOf(it[0], i.value[0]) < minOf(i.value[1], it[1]) }.keys }
            .filter { it.value.isNotEmpty() }
    }

    fun diStringMatch(s: String): IntArray {
        for (i in 0..s.lastIndex - 1) {

        }
        return intArrayOf()
    }

    @Test
    fun testl1() {
        largestInteger(1234)
    }

    val vowel = listOf('a', 'e', 'i', 'o', 'u')

    fun isValid(word: String): Boolean {
        if (word.length < 3) return false

        if (!word.all { it.isLetterOrDigit()}) {
            return false
        }

        if (!word.any { vowel.any { v -> v.equals(it, ignoreCase = true) } }) {
            return false
        }

        if (word.all { vowel.any { v -> v.equals(it, ignoreCase = true) } || it.isDigit()  }) {
            return false
        }

        return true
    }

    fun createTargetArray(nums: IntArray, index: IntArray): IntArray {
        val res = mutableListOf<Int>()
        for(i in nums.indices) {
            res.add(index[i], nums[i])
        }
        return res.toIntArray()
    }

    fun largestInteger(num: Int): Int {
        val q1 = PriorityQueue<Int>()
        val q2 = PriorityQueue<Int>()

        val vv = num.toString().map { it.digitToInt() }.map {
            if (it % 2 == 0) {
                q1.add(it)
                true
            } else {
                q2.add(it)
                false
            }
        }


        return buildString {
             vv.forEach {
                val n = if(it) {
                    q1.poll()
                } else {
                    q2.poll()
                }
                 append(n)
            }

        }.toInt()
    }

    fun maxPower(s: String): Int {
        var l = 0
        var ss = 0
        for(i in 0..s.lastIndex - 1) {
            if (s[i] == s[i + 1]) {
                ss += 1
                l = max(l, ss)
            } else {
                ss = 0
            }
        }
        return l
    }
}
