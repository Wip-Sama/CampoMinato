package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.Cell
import CampoMinato.Model.CellStatus
import CampoMinato.Model.Statuses

class Doubted : CellStatus() {
    override fun isDoubted() : Boolean {
        return true
    }

    override fun leftClick(cell: Cell) {
        // Do nothing
    }

    override fun rightClick(cell: Cell) {
        cell.stateProperty.set(Hidden())
    }

    override fun getStatusType(): Statuses {
        return Statuses.DOUBTED
    }
}