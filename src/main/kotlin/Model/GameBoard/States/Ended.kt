package CampoMinato.Model.GameBoard.States

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.States.Revealed
import CampoMinato.Model.GameBoard.GameBoard
import CampoMinato.Model.GameBoard.GameBoardState

object Ended : GameBoardState {
    override fun isEnded(): Boolean {
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
        return true
    }

    override fun lose(gameBoard: GameBoard): Boolean {
        val iterator = gameBoard.gameBoardIterator()
        while (iterator.hasNext()) {
            val cell = iterator.next()
            if (cell.isExploded()) {
                return true
            }
        }
        return false
    }

    override fun revealAllCells(gameBoard: GameBoard) {
        val iterator = gameBoard.gameBoardIterator()
        while (iterator.hasNext()) {
            val cell = iterator.next()
            cell.getStateProperty().set(Revealed)
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