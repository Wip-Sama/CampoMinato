package CampoMinato.Model.Cell.States

import CampoMinato.Model.*
import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.Enums.CellStates
import CampoMinato.Model.Cell.CellState

object Revealed : CellState {
    override fun isRevealed() : Boolean {
        return true
    }

    override fun toString(cell: Cell): String {
        if (cell.isBomb())
            return "R"
        return "r"
    }

    override fun leftClick(cell: Cell) {
        // Do nothing
    }

    override fun rightClick(cell: Cell) {
        // Do nothing
    }

    override fun doubleLeftClick(cell: Cell) {
        GameController.getGameBoard().value.getCellPosition(cell).let { (x, y) ->
            GameController.getGameBoard().value.revealNeighbors(x, y)
        }
    }

    override fun getStatusType(): CellStates {
        return CellStates.REVEALED
    }

    override fun getDisplayValue(cell: Cell): String {
        GameController.getGameBoard().value.getCellPosition(cell).let { (x, y) ->
            val bombCount = GameController.getGameBoard().value.searchBombs(x, y)
            if (bombCount == 0)
                return ""
            return bombCount.toString()
        }
    }
}