package activation

import Player

class TableActivationFunction4(
    values: List<Double>,
) : ActivationFunction {

    private val valuesMap = mapOf(
        indices() to values[0],
        indices(0) to values[1],
        indices(1) to values[2],
        indices(2) to values[3],
        indices(3) to values[4],
        indices(0,1) to values[5],
        indices(0,2) to values[6],
        indices(0,3) to values[7],
        indices(1,2) to values[8],
        indices(1,3) to values[9],
        indices(2,3) to values[10],
        indices(0,1,2) to values[11],
        indices(0,1,3) to values[12],
        indices(0,2,3) to values[13],
        indices(1,2,3) to values[14],
        indices(0,1,2,3) to values[15],
    )

    override fun invoke(subset: Set<Player>): Double {
        return valuesMap[subset.map { it.index }.toSet()] ?: throw IllegalArgumentException("Unknown subset: ${subset.map { it.index }}")
    }

    private fun indices(vararg indices: Int): Set<Int> = indices.toSet()
}