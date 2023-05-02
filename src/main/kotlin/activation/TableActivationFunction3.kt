package activation

import Player

class TableActivationFunction3(
    values: List<Double>
) : ActivationFunction {
    private val valuesMap = mapOf(
        indices() to values[0],
        indices(0) to values[1],
        indices(1) to values[2],
        indices(2) to values[3],
        indices(0,1) to values[4],
        indices(0,2) to values[5],
        indices(1,2) to values[6],
        indices(0,1,2) to values[7],
    )

    override fun invoke(subset: Set<Player>): Double {
        return valuesMap[subset.map { it.index }.toSet()] ?: throw IllegalArgumentException("Unknown subset: ${subset.map { it.index }}")
    }

    private fun indices(vararg indices: Int): Set<Int> = indices.toSet()
}