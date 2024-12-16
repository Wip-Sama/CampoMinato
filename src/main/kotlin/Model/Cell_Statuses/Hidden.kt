package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.Cell
import CampoMinato.Model.CellStatus
import CampoMinato.Model.Statuses

class Hidden : CellStatus() {
    override fun isHidden() : Boolean {
        return true
    }

    override fun leftClick(cell: Cell) {
        if (cell.isBomb) {
            cell.stateProperty.set(Exploded())
        } else {
            cell.stateProperty.set(Revealed())
        }
    }

    override fun rightClick(cell: Cell) {
        cell.stateProperty.set(Flagged())
    }

    override fun getStatusType(): Statuses {
        return Statuses.HIDDEN
    }
}