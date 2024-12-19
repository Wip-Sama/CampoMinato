package CampoMinato.Model.States.Cell_Types

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.CellState
import CampoMinato.Model.Cell_Statuses.Hidden

class EmptyCell(state: CellState = Hidden) : Cell(state) {
    override val isBomb: Boolean = false

    override fun clone(): Cell {
        return EmptyCell(stateProperty.get())
    }
}