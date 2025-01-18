package CampoMinato.Model.SessionHistory

import CampoMinato.Model.Scoreboard.GameHistory

interface Container: GameHistory {
	val parent: Container?
	val children: MutableList<GameHistory>
	val isLeaf: Boolean
		get() = children.isEmpty()
	val isRoot: Boolean
		get() = parent == null
	fun add(comp: GameHistory) {
		children.add(comp)
	}
	fun remove(comp: Container) {
		children.remove(comp)
	}
}