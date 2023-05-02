package utils

object NumberUtils {
    fun factorial(n: Int): Int {
        var result = 1
        for (i in 2 .. n) {
            result *= i
        }
        return result
    }

    fun c(n: Int, k: Int): Int {
        return factorial(n) / (factorial(k) * factorial(n-k))
    }

    fun c(n: Int, kRange: IntRange): Int {
        return kRange.sumOf { c(n, it) }
    }
}