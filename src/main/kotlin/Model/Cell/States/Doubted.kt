package CampoMinato.Model.Cell.States

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.Enums.CellStates
import CampoMinato.Model.Cell.CellState

object Doubted : CellState {
    override fun isDoubted() : Boolean {
        return true
    }

    override fun toString(cell: Cell): String {
        if (cell.isBomb())
            return "D"
        return "d"
    }

    override fun leftClick(cell: Cell) {
        // Do nothing
    }

    override fun rightClick(cell: Cell) {
        cell.getStateProperty().set(Hidden)
    }

    override fun doubleLeftClick(cell: Cell) {
        leftClick(cell)
    }

    override fun getStatusType(): CellStates {
        return CellStates.DOUBTED
    }

    override fun getDisplayValue(cell: Cell): String {
        return "❓"
    }
}