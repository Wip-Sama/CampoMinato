package CampoMinato.Model.SessionHistory

import CampoMinato.Model.Scoreboard.GameHistory

//Component
open class GenericContainer(override val parent: Container? = null) : Container {
	override val children: MutableList<GameHistory> = mutableListOf()
	override fun getSessionBoard(): String {
		var score = ""
		for (child in children) {
			score += child.getSessionBoard()+"\n"
		}
		return score
	}
}