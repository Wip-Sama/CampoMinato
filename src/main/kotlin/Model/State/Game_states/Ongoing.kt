package CampoMinato.Model.State.Game_states

import CampoMinato.Model.States.GameState

object Ongoing : GameState() {
    override fun isOngoing(): Boolean {
        return true
    }
}