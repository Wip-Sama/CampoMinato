package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.Cell
import CampoMinato.Model.CellStatus
import CampoMinato.Model.Statuses

class Exploded : CellStatus() {
    override fun isRevealed() : Boolean {
        return true
    }

    override fun leftClick(cell: Cell) {
        // Do nothing
    }

    override fun rightClick(cell: Cell) {
        // Do nothing
    }

    override fun getStatusType(): Statuses {
        return Statuses.EXPLODED
    }
}