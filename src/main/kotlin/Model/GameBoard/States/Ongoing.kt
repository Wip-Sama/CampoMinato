package CampoMinato.Model.GameBoard.States

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.States.Hidden
import CampoMinato.Model.GameBoard.GameBoard
import CampoMinato.Model.GameBoard.GameBoardState

object Ongoing : GameBoardState {
    override fun isOngoing(): Boolean {
        return true
    }

    override fun won(gameBoard: GameBoard): Boolean {
        val iterator = gameBoard.gameBoardIterator()
        while (iterator.hasNext()) {
            val cell = iterator.next()
            if (cell.isHidden() && !cell.isBomb()) {
                return false
            }
        }
        gameBoard.gameStateProperty.set(Ended)
        return true
    }

    override fun lose(gameBoard: GameBoard): Boolean {
        val iterator = gameBoard.gameBoardIterator()
        while (iterator.hasNext()) {
            val cell = iterator.next()
            if (cell.isExploded()) {
                gameBoard.gameStateProperty.set(Ended)
                return true
            }
        }
        return false
    }

    override fun revealAllCells(gameBoard: GameBoard) {
        //do nothing
    }

    override fun revealNeighbors(gameBoard: GameBoard, x: Int, y: Int) {
        val iterator = gameBoard.rangedGameBoardIterator(x, y, 1)
        while (iterator.hasNext()) {
            val cell = iterator.next()
            if (cell.isHidden()) {
                cell.leftClick()
            }
        }
    }

    override fun revealEmptyCells(gameBoard: GameBoard, cell: Cell) {
        val (x, y) = gameBoard.getCellPosition(cell)
        if (gameBoard.searchBombs(x, y) == 0) {
            val iterator = gameBoard.rangedGameBoardIterator(x, y, 1)
            while (iterator.hasNext()) {
                val c = iterator.next()
                if (c.isHidden()) {
                    c.leftClick()
                }
            }
        }
    }

    override fun hideAllCells(gameBoard: GameBoard) {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                gameBoard.cells[x][y].getStateProperty().set(Hidden)
            }
        }
    }
}