import activation.TableActivationFunction3
import activation.TableActivationFunction4
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import java.io.File

fun main(args: Array<String>) {
    val mapper = configMapper()
    val task = mapper.readValue(File("lr6.json"), Lr6Task::class.java)
    val f = TableActivationFunction4(task.activationFunction)
    val players = (0 until task.playersCount).map { Player(it, "${it+1}") }.toSet()
    //val f = TableActivationFunction3(listOf(0,1,1,1,3,3,3,4).map { it.toDouble() })
    //val players = players(0,1,2)
    val game = CooperativeGame(players, f)
    game.testIsSuperAdditive()
    game.testIsConvex()
    game.calcShapleyValue()
}

private fun players(vararg indexes: Int): Set<Player> = setOf(*indexes.toTypedArray()).map { Player(it, "${it+1}") }.toSet()

private fun configMapper(): ObjectMapper {
    val mapper = ObjectMapper()
    mapper.registerModule(kotlinModule())
    mapper.enable(JsonParser.Feature.ALLOW_COMMENTS)
    return mapper
}