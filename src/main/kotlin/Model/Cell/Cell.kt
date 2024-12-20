package CampoMinato.Model.Cell

import CampoMinato.Model.Cell.States.Hidden
import CampoMinato.Model.Cell.Enums.CellStates
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.SimpleObjectProperty

interface Cell {
    fun getDisplayValue(): String
    fun isHidden() : Boolean
    fun isDoubted() : Boolean
    fun isFlagged() : Boolean
    fun isExploded() : Boolean
    fun isRevealed() : Boolean
    fun getStatusType(): CellStates
    fun leftClick()
    fun rightClick()
    fun doubleLeftClick()
    fun getStateProperty(): SimpleObjectProperty<CellState>
    fun isBomb(): Boolean
    fun getId(): Int
}