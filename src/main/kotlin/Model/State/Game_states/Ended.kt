package CampoMinato.Model.State.Game_states

import CampoMinato.Model.States.GameState

object Ended : GameState() {
    override fun isEnded() : Boolean {
        return true
    }
}