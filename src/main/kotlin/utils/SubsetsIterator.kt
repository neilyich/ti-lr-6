package utils

import Player

class SubsetsIterator(
    private val players: Set<Player>,
) : Iterator<Set<Player>> {
    private var index = 0
    private val subsetsCount = 1.shl(players.size)
    override fun hasNext(): Boolean {
        return index < subsetsCount
    }

    override fun next(): Set<Player> {
        return currentSubset().also { index++ }
    }

    private fun currentSubset(): Set<Player> {
        val subset = mutableSetOf<Player>()
        for (player in players) {
            val mask = 1.shl(player.index)
            if (mask.and(index) != 0) {
                subset.add(player)
            }
        }
        return subset
    }
}
