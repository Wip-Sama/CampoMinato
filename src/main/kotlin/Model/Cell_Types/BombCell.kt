package CampoMinato.Model.States.Cell_Types

import CampoMinato.Model.Cell
import CampoMinato.Model.States.CellState
import CampoMinato.Model.Cell_Statuses.Hidden

class BombCell(state: CellState = Hidden) : Cell(state) {
    override val isBomb: Boolean = true

    override fun clone(): Cell {
        return BombCell(stateProperty.get())
    }
}