package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.*
import CampoMinato.Model.Enums.CellStates
import CampoMinato.Model.States.CellState

object Hidden : CellState() {
    override fun isHidden() : Boolean {
        return true
    }

    override fun toString(cell: Cell): String {
        if (cell.isBomb)
            return "H"
        return "h"
    }

    override fun leftClick(cell: Cell) {
        if (cell.isBomb) {
            cell.stateProperty.set(Exploded)
        } else {
            cell.stateProperty.set(Revealed)
            GameBoard.discoverBombs(cell)
        }
    }

    override fun rightClick(cell: Cell) {
        cell.stateProperty.set(Flagged)
    }

    override fun doubleLeftClick(cell: Cell) {
        leftClick(cell)
    }

    override fun getStatusType(): CellStates {
        return CellStates.HIDDEN
    }

    override fun getDisplayValue(cell: Cell): String {
        return ""
    }
}