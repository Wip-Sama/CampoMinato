package CampoMinato.Model.SessionHistory

//Component
class Difficulty(private val diff: Int, override val parent: Container? = null) : GenericContainer(parent) {
	override fun getSessionBoard(): String {
		var score = "diff: $diff\n"
		for (child in children) {
			score += "      "+child.getSessionBoard()+"\n"
		}
		return score
	}
}