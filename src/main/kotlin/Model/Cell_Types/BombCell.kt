package CampoMinato.Model.Cell_Types

import CampoMinato.Model.Cell
import CampoMinato.Model.CellStatus
import CampoMinato.Model.Cell_Statuses.Hidden

class BombCell(state: CellStatus = Hidden()) : Cell(state) {
    override val isBomb: Boolean = true

    override fun clone(): Cell {
        return BombCell(stateProperty.get())
    }
}