package CampoMinato.Model.State.Game_states

import CampoMinato.Model.Cell
import CampoMinato.Model.Cell_Statuses.Revealed
import CampoMinato.Model.GameBoard
import CampoMinato.Model.States.GameState

object Ended : GameState() {
    override fun isEnded() : Boolean {
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
        return true
    }

    override fun lose(gameBoard: GameBoard): Boolean {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                if (gameBoard.cells[x][y].isExploded()) {
                    return true
                }
            }
        }
        return false
    }

    override fun revealAllCells(gameBoard: GameBoard) {
        for (x in 0 until gameBoard.rows) {
            for (y in 0 until gameBoard.columns) {
                gameBoard.cells[x][y].stateProperty.set(Revealed)
            }
        }
    }

    override fun hideAllCells(gameBoard: GameBoard) {
        //do nothing
    }

    override fun revealEmptyCells(gameBoard: GameBoard, cell: Cell) {
        //do nothing
    }

    override fun revealNeighbors(gameBoard: GameBoard, x: Int, y: Int) {
        //do nothing
    }
}