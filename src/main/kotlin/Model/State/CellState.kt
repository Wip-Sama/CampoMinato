package CampoMinato.Model.States

import CampoMinato.Model.Cell
import CampoMinato.Model.CellFactory
import CampoMinato.Model.Enums.CellStates

abstract class CellState() {
    open fun isHidden() : Boolean {
        return false
    }

    open fun isDoubted() : Boolean {
        return false
    }

    open fun isFlagged() : Boolean {
        return false
    }

    open fun isRevealed() : Boolean {
        return false
    }

    open fun isExploded() : Boolean {
        return false
        var ciao = CellFactory.build()
    }

    //Does nothing by default
    open fun doubleLeftClick(cell: Cell) {}

    abstract fun toString(cell: Cell): String

    abstract fun leftClick(cell: Cell)

    abstract fun rightClick(cell: Cell)

    abstract fun getStatusType(): CellStates

    abstract fun getDisplayValue(cell: Cell): String
}