package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.*
import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.Enums.CellStates
import CampoMinato.Model.Cell.CellState

object Hidden : CellState {
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
            GameController.getGameBoard().value.revealEmptyCells(cell)
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