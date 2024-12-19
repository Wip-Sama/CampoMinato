package CampoMinato.Model.Cell

import CampoMinato.Model.GameController

// Proxy pattern
class CellProxy(private val cell: Cell) : Cell() {
    override val isBomb: Boolean
        get() = cell.isBomb

    override fun clone(): Cell {
        return cell.clone()
    }

    override fun leftClick() {
        if (GameController.getGameBoard().value.isOngoing())
            super.leftClick()
    }

    override fun rightClick() {
        if (GameController.getGameBoard().value.isOngoing())
            super.rightClick()
    }

    override fun doubleLeftClick() {
        if (GameController.getGameBoard().value.isOngoing())
            super.doubleLeftClick()
    }
}