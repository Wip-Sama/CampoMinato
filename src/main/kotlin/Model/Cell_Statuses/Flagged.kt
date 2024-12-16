package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.Cell
import CampoMinato.Model.CellStatus
import CampoMinato.Model.Statuses

class Flagged : CellStatus() {
    override fun isFlagged() : Boolean {
        return true
    }

    override fun leftClick(cell: Cell) {
        // Do nothing
    }

    override fun rightClick(cell: Cell) {
        cell.stateProperty.set(Doubted())
    }

    override fun getStatusType(): Statuses {
        return Statuses.FLAGGED
    }
}