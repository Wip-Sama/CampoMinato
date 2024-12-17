package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.*
import CampoMinato.Model.Enums.CellStates
import CampoMinato.Model.States.CellState

object Exploded : CellState() {
    override fun isExploded() : Boolean {
        return true
    }

    override fun toString(cell: Cell): String {
        if (cell.isBomb)
            return "E"
        return "e"
    }

    override fun leftClick(cell: Cell) {
        // Do nothing
    }

    override fun rightClick(cell: Cell) {
        // Do nothing
    }

    override fun doubleLeftClick(cell: Cell) {
        leftClick(cell)
    }

    override fun getStatusType(): CellStates {
        return CellStates.EXPLODED
    }

    override fun getDisplayValue(cell: Cell): String {
        return "\uD83D\uDCA3" //💣
    }
}