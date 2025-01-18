package CampoMinato.Model.SessionHistory

import CampoMinato.Model.Scoreboard.GameHistory

//Leaf
class Game(private val state: String): GameHistory {
	override fun getSessionBoard(): String {
		return state
	}
}