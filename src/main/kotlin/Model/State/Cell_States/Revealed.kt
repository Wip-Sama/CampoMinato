package CampoMinato.Model.Cell_Statuses

import CampoMinato.Model.*
import CampoMinato.Model.Enums.CellStates
import CampoMinato.Model.States.CellState

object Revealed : CellState() {
    override fun isRevealed() : Boolean {
        return true
    }

    override fun toString(cell: Cell): String {
        if (cell.isBomb)
            return "R"
        return "r"
    }

    override fun leftClick(cell: Cell) {
        // Do nothing
        println("here")
    }

    override fun rightClick(cell: Cell) {
        // Do nothing
    }

    override fun doubleLeftClick(cell: Cell) {
        println("Revealed neighbor cells")
        GameBoard.getCellPosition(cell).let { (x, y) ->
            GameBoard.revealNeighbors(x, y)
        }
    }

    override fun getStatusType(): CellStates {
        return CellStates.REVEALED
    }

    override fun getDisplayValue(cell: Cell): String {
        GameBoard.getCellPosition(cell).let { (x, y) ->
            val bombCount = GameBoard.searchBombs(x, y)
            if (bombCount == 0)
                return ""
            return bombCount.toString()
        }
    }
}