package CampoMinato.Model.Cell

import CampoMinato.Model.Cell.Enums.CellStates
import CampoMinato.Model.GameController
import javafx.beans.property.SimpleObjectProperty

// Proxy pattern
class CellProxy(cellImplementation: CellImplementation) : Cell {
    private val cell: CellImplementation = cellImplementation

    override fun getDisplayValue(): String {
        return cell.getDisplayValue()
    }

    override fun isHidden(): Boolean {
        return cell.isHidden()
    }

    override fun isDoubted(): Boolean {
        return cell.isDoubted()
    }

    override fun isFlagged(): Boolean {
        return cell.isFlagged()
    }

    override fun isExploded(): Boolean {
        return cell.isExploded()
    }

    override fun isRevealed(): Boolean {
        return cell.isRevealed()
    }

    override fun getStatusType(): CellStates {
        return cell.getStatusType()
    }

    override fun leftClick() {
        if (GameController.getGameBoard().value.isOngoing())
            cell.leftClick()
    }

    override fun rightClick() {
        if (GameController.getGameBoard().value.isOngoing())
            cell.rightClick()
    }

    override fun doubleLeftClick() {
        if (GameController.getGameBoard().value.isOngoing())
            cell.doubleLeftClick()
    }

    override fun getStateProperty(): SimpleObjectProperty<CellState> {
        return cell.getStateProperty()
    }

    override fun isBomb(): Boolean {
        return cell.isBomb()
    }

    override fun getId(): Int {
        return cell.getId()
    }

    override fun toString(): String {
        return cell.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        return this.cell == other
    }
}