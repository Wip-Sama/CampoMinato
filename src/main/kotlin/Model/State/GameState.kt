package CampoMinato.Model.States

import CampoMinato.Model.Cell
import CampoMinato.Model.GameBoard

abstract class GameState {
    open fun isOngoing() : Boolean {
        return false
    }

    open fun isEnded() : Boolean {
        return false
    }

    abstract fun won(gameBoard: GameBoard): Boolean

    abstract fun lose(gameBoard: GameBoard): Boolean

    open fun countFlags(gameBoard : GameBoard) : Int {
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

    open fun searchBombs(gameBoard: GameBoard, x: Int, y: Int): Int {
        var bombs = 0
        for (i in -1..1) {
            for (j in -1..1) {
                if (x + i in 0 until gameBoard.rows && y + j in 0 until gameBoard.columns) {
                    if (gameBoard.cells[x + i][y + j].isBomb) {
                        bombs++
                    }
                }
            }
        }
        return bombs
    }

    open fun getCellPosition(gameBoard: GameBoard, cell: Cell): Pair<Int, Int> {
        for (x in 0..<gameBoard.rows) {
            for (y in 0..<gameBoard.columns) {
                if (gameBoard.cells[x][y] == cell) {
                    return Pair(x, y)
                }
            }
        }
        return Pair(-1, -1)
    }

    open fun revealAllBombs(gameBoard: GameBoard) {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                if (gameBoard.cells[x][y].isBomb) {
                    gameBoard.cells[x][y].leftClick()
                }
            }
        }
    }

    abstract fun revealAllCells(gameBoard: GameBoard)

    abstract fun hideAllCells(gameBoard: GameBoard)

    abstract fun revealEmptyCells(gameBoard: GameBoard, cell: Cell)

    abstract fun revealNeighbors(gameBoard: GameBoard, x: Int, y: Int)
}
