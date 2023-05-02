import activation.ActivationFunction
import utils.FormattedPrinter
import utils.NumberUtils.factorial
import utils.SubsetsIterator

class CooperativeGame(
    private val players: Set<Player>,
    private val f: ActivationFunction,
) {

    fun testIsSuperAdditive() {
        println()
        //println("Проверка игры на супераддитивность:")
        val S = Iterable { SubsetsIterator(players) }
        for (s in S) {
            val T = Iterable { SubsetsIterator(players.subtract(s)) }
            for (t in T) {
                if (f(s+t) < (f(s) + f(t))) {
                    println("Игра не является супераддитивной:")
                    FormattedPrinter.print("S" to s, "T" to t)
                    FormattedPrinter.printFormat("f(SvT)=%s < f(S)=%s + f(T)=%s", f(s+t), f(s), f(t))
                    return
                }
            }
        }
        println("Игра является супераддитивной")
    }

    fun testIsConvex() {
        println()
        //println("Проверка игры на выпуклость:")
        val S = Iterable { SubsetsIterator(players) }
        val T = Iterable { SubsetsIterator(players) }
        for (s in S) {
            for (t in T) {
                if (f(s+t) + f(s.intersect(t)) < f(s) + f(t)) {
                    println("Игра не является выпуклой:")
                    FormattedPrinter.print("S" to s, "T" to t)
                    FormattedPrinter.printFormat("f(SvT)=%s + f(S*T)=%s < f(S)=%s + f(T)=%s", f(s + t), f(s.intersect(t)), f(s), f(t))
                    return
                }
            }
        }
        println("Игра является выпуклой")
    }

    fun calcShapleyValue() {
        println()
        val S = Iterable { SubsetsIterator(players) }
        val shapleyValue = players.map { player ->
            val coef = 1.0 / factorial(players.size)
            var sum = 0.0
            for (s in S.filter { it.contains(player) }) {
                sum += factorial(s.size-1) * factorial(players.size-s.size) * (f(s) - f(s-player))
            }
            player to coef * sum
        }.toMap()
        FormattedPrinter.print("Вектор Шепли", shapleyValue.values.toList())
        val sum = shapleyValue.values.sum()
        val expectedSum = f(players)
        if (sum == expectedSum) {
            FormattedPrinter.printFormat("Условие групповой рационализации выполнено: v(I)=%s, sum(Xi)=%s", expectedSum, sum)
        } else {
            FormattedPrinter.printFormat("Условие групповой рационализации не выполнено: v(I)=%s != sum(Xi)=%s", expectedSum, sum)
        }
        println("Проверка условий индивидуальной рационализации:")
        for (player in players) {
            val solo = f(setOf(player))
            val shapley = shapleyValue[player] ?: 0.0
            if (shapley >= solo) {
                FormattedPrinter.printFormat("Выполнено (игрок ${player.name}): Xi(v)=%s >= v({i})=%s", shapley, solo)
            } else {
                FormattedPrinter.printFormat("Не выполнено (игрок ${player.name}): Xi(v)=%s <= v({i})=%s", shapley, solo)
            }
        }
    }

}