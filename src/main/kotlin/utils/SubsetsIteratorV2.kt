package utils

import Player

class SubsetsIteratorV2(
    private val players: Set<Player>,
) : Iterator<Set<Player>> {

    private var weight = 0

    private var currentIndices = listOf<Int>()

    override fun hasNext(): Boolean {
        TODO("Not yet implemented")
    }

    override fun next(): Set<Player> {
        return currentIndices.map { index -> players.find { it.index == index }!! }.toSet().also {
            prepareNextIndices()
        }
    }

    private fun prepareNextIndices() {

    }
}