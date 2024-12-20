package CampoMinato.Model.GameBoard

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.States.Revealed

interface GameBoardState {
    fun isOngoing(): Boolean {
        return false
    }

    fun isEnded(): Boolean {
        return false
    }

    fun countFlags(gameBoard: GameBoard): Int {
        var flags = 0
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                if (gameBoard.cells[x][y].isFlagged()) {
                    flags++
                }
            }
        }
        return flags
    }

    fun countNearbyBombs(gameBoard: GameBoard, x: Int, y: Int): Int {
        val iterator = gameBoard.rangedGameBoardIterator(x, y, 1)
        var bombs = 0
        while (iterator.hasNext()) {
            val cell = iterator.next()
            if (cell.isBomb()) {
                bombs++
            }
        }
        return bombs
    }

    fun getCellPosition(gameBoard: GameBoard, cell: Cell): Pair<Int, Int> {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                if (gameBoard.cells[x][y] == cell) {
                    return Pair(x, y)
                }
            }
        }
        return Pair(-1, -1)
    }

    fun revealAllBombs(gameBoard: GameBoard) {
        val iterator = gameBoard.gameBoardIterator()
        if (iterator.hasNext()) {
            val cell = iterator.next()
            if (cell.isBomb()) {
                cell.getStateProperty().set(Revealed)
            }
        }
    }

    fun won(gameBoard: GameBoard): Boolean
    fun lose(gameBoard: GameBoard): Boolean
    fun revealAllCells(gameBoard: GameBoard)
    fun hideAllCells(gameBoard: GameBoard)
    fun revealEmptyCells(gameBoard: GameBoard, cell: Cell)
    fun revealNeighbors(gameBoard: GameBoard, x: Int, y: Int)
}
