package activation

import Player

interface ActivationFunction {
    operator fun invoke(subset: Set<Player>): Double
}