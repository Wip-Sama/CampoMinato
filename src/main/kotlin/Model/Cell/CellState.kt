package CampoMinato.Model.Cell

import CampoMinato.Model.Cell.Enums.CellStates

interface CellState {
    fun isHidden() : Boolean {
        return false
    }

    fun isDoubted() : Boolean {
        return false
    }

    fun isFlagged() : Boolean {
        return false
    }

    fun isRevealed() : Boolean {
        return false
    }

    fun isExploded() : Boolean {
        return false
    }

    //Does nothing by default
    fun doubleLeftClick(cell: Cell) {}

    fun toString(cell: Cell): String

    fun leftClick(cell: Cell)

    fun rightClick(cell: Cell)

    fun getStatusType(): CellStates

    fun getDisplayValue(cell: Cell): String
}