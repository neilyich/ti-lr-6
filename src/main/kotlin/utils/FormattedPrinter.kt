package utils

import Player
import java.math.BigDecimal
import java.math.RoundingMode

object FormattedPrinter {
    private var SCALE = 3

    fun setScale(scale: Int) {
        SCALE = scale
    }

    fun print(label: String?, players: Iterable<Player>, newLine: Boolean = false) {
        label?.let { print("$it=") }
        val sortedPlayers = players.sortedBy { it.index }
        print(sortedPlayers.map { it.name }, newLine)
    }

    fun print(vararg pairs: Pair<String?, Iterable<Player>>) {
        for (i in pairs.indices) {
            val pair = pairs[i]
            print(pair.first, pair.second)
            if (i < pairs.size - 1) {
                print(", ")
            }
        }
        println()
    }

    fun print(label: String?, shapleyValue: List<Double>, newLine: Boolean = true) {
        label?.let { print("$it: ") }
        print(shapleyValue.map { roundDouble(it) }, newLine)
    }

    fun printFormat(format: String, vararg numbers: Double) {
        println(String.format(format, *numbers.map { roundDouble(it) }.toTypedArray()))
    }

    private fun print(any: Any, newLine: Boolean) {
        if (newLine) {
            println(any)
        } else {
            print(any)
        }
    }

    private fun roundDouble(n: Double): String {
        val str = BigDecimal.valueOf(n).setScale(SCALE, RoundingMode.HALF_EVEN).toString()
        var lastNonZeroIndex = str.length - 1
        while (lastNonZeroIndex > 0 && str[lastNonZeroIndex] == '0') {
            lastNonZeroIndex--
        }
        if (lastNonZeroIndex > 0 && str[lastNonZeroIndex] == '.') {
            lastNonZeroIndex--
        }
        return str.substring(0, lastNonZeroIndex + 1)
    }
}