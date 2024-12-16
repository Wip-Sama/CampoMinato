package CampoMinato.Model.Cell_Types

import CampoMinato.Model.Cell
import CampoMinato.Model.CellStatus
import CampoMinato.Model.Cell_Statuses.Hidden

class EmptyCell(state: CellStatus = Hidden()) : Cell(state) {
    override val isBomb: Boolean = false

    override fun clone(): Cell {
        return EmptyCell(stateProperty.get())
    }
}