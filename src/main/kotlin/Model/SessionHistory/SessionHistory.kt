package CampoMinato.Model.Scoreboard

import CampoMinato.Model.SessionHistory.Container
import CampoMinato.Model.SessionHistory.GenericContainer

//Component, Root, Singleton
object SessionHistory: GenericContainer() {
	override val parent: Container?
		get() = null

	override fun getSessionBoard(): String {
		var s = ""
		for (child in children) {
			s += child.getSessionBoard()+"\n"
		}
		return s
	}
}