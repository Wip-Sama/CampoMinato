package CampoMinato.Model.GameBoard.Game_states

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell_Statuses.Hidden
import CampoMinato.Model.GameBoard.GameBoard
import CampoMinato.Model.GameBoard.GameBoardState

object Ongoing : GameBoardState {
    override fun isOngoing(): Boolean {
        return true
    }

    override fun won(gameBoard: GameBoard): Boolean {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                if (gameBoard.cells[x][y].isHidden() && !gameBoard.cells[x][y].isBomb) {
                    return false
                }
            }
        }
        gameBoard.gameStateProperty.set(Ended)
        return true
    }

    override fun lose(gameBoard: GameBoard): Boolean {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                if (gameBoard.cells[x][y].isExploded()) {
                    gameBoard.gameStateProperty.set(Ended)
                    return true
                }
            }
        }
        return false
    }

    override fun revealAllCells(gameBoard: GameBoard) {
        //do nothing
    }

    override fun revealNeighbors(gameBoard: GameBoard, x: Int, y: Int) {
        for (i in -1..1) {
            for (j in -1..1) {
                if (x + i in 0 until gameBoard.rows && y + j in 0 until gameBoard.columns) {
                    if (gameBoard.cells[x + i][y + j].isHidden()) {
                        gameBoard.cells[x + i][y + j].leftClick()
                    }
                }
            }
        }
    }

    override fun revealEmptyCells(gameBoard: GameBoard, cell: Cell) {
        val (x, y) = gameBoard.getCellPosition(cell)
        if (gameBoard.searchBombs(x, y) == 0) {
            for (i in -1..1) {
                for (j in -1..1) {
                    if (x + i in 0 until gameBoard.rows && y + j in 0 until gameBoard.columns) {
                        if (gameBoard.cells[x + i][y + j].isHidden()) {
                            gameBoard.cells[x + i][y + j].leftClick()
                        }
                    }
                }
            }
        }
    }

    override fun hideAllCells(gameBoard: GameBoard) {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                gameBoard.cells[x][y].stateProperty.set(Hidden)
            }
        }
    }
}