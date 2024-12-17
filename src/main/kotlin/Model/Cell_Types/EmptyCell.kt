package CampoMinato.Model.States.Cell_Types

import CampoMinato.Model.Cell
import CampoMinato.Model.States.CellState
import CampoMinato.Model.Cell_Statuses.Hidden

class EmptyCell(state: CellState = Hidden) : Cell(state) {
    override val isBomb: Boolean = false

    override fun clone(): Cell {
        return EmptyCell(stateProperty.get())
    }
}