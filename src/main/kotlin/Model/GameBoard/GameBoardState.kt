package CampoMinato.Model.GameBoard

import CampoMinato.Model.Cell.Cell

interface GameBoardState {
    fun isOngoing() : Boolean {
        return false
    }

    fun isEnded() : Boolean {
        return false
    }

    fun won(gameBoard: GameBoard): Boolean

    fun lose(gameBoard: GameBoard): Boolean

    fun countFlags(gameBoard : GameBoard) : Int {
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

    fun searchBombs(gameBoard: GameBoard, x: Int, y: Int): Int {
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

    fun getCellPosition(gameBoard: GameBoard, cell: Cell): Pair<Int, Int> {
        for (x in 0..<gameBoard.rows) {
            for (y in 0..<gameBoard.columns) {
                if (gameBoard.cells[x][y] == cell) {
                    return Pair(x, y)
                }
            }
        }
        return Pair(-1, -1)
    }

    fun revealAllBombs(gameBoard: GameBoard) {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                if (gameBoard.cells[x][y].isBomb) {
                    gameBoard.cells[x][y].leftClick()
                }
            }
        }
    }

    fun revealAllCells(gameBoard: GameBoard)

    fun hideAllCells(gameBoard: GameBoard)

    fun revealEmptyCells(gameBoard: GameBoard, cell: Cell)

    fun revealNeighbors(gameBoard: GameBoard, x: Int, y: Int)
}
