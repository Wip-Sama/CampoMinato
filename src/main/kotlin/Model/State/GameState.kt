package CampoMinato.Model.States

open class GameState {
    open fun isOngoing() : Boolean {
        return false
    }

    open fun isEnded() : Boolean {
        return false
    }
}
