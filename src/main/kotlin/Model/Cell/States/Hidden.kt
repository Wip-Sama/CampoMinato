package CampoMinato.Model.Cell.States

import CampoMinato.Model.*
import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.Enums.CellStates
import CampoMinato.Model.Cell.CellState

object Hidden : CellState {
    override fun isHidden() : Boolean {
        return true
    }

    override fun toString(cell: Cell): String {
        if (cell.isBomb())
            return "H"
        return "h"
    }

    override fun leftClick(cell: Cell) {
        if (cell.isBomb()) {
            cell.getStateProperty().set(Exploded)
        } else {
            cell.getStateProperty().set(Revealed)
            GameController.getGameBoard().value.revealEmptyCells(cell)
        }
    }

    override fun rightClick(cell: Cell) {
        cell.getStateProperty().set(Flagged)
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