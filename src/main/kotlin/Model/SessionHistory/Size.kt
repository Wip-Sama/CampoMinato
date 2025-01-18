package CampoMinato.Model.Scoreboard

import CampoMinato.Model.SessionHistory.Container
import CampoMinato.Model.SessionHistory.GenericContainer

//Component
class Size(private val dim: String, override val parent: Container? = null) : GenericContainer(parent) {
	override fun getSessionBoard(): String {
		var score = "size: $dim\n"
		for (child in children) {
			score += "  "+child.getSessionBoard()+"\n"
		}
		return score
	}
}