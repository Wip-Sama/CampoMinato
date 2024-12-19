package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.Enums.CellStates
import CampoMinato.Model.Cell.CellState

object Flagged : CellState {
    override fun isFlagged() : Boolean {
        return true
    }

    override fun toString(cell: Cell): String {
        if (cell.isBomb)
            return "F"
        return "f"
    }


    override fun leftClick(cell: Cell) {
        // Do nothing
    }

    override fun rightClick(cell: Cell) {
        cell.stateProperty.set(Doubted)
    }

    override fun doubleLeftClick(cell: Cell) {
        leftClick(cell)
    }

    override fun getStatusType(): CellStates {
        return CellStates.FLAGGED
    }

    override fun getDisplayValue(cell: Cell): String {
        return "\uD83D\uDEA9" //🚩
    }
}