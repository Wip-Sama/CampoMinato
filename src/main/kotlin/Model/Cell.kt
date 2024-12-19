package CampoMinato.Model

import CampoMinato.Model.Cell_Statuses.Hidden
import CampoMinato.Model.Enums.CellStates
import CampoMinato.Model.States.CellState
import javafx.beans.property.SimpleObjectProperty

abstract class Cell {
    abstract val isBomb: Boolean
    val stateProperty = SimpleObjectProperty<CellState>(Hidden)

    constructor(state: CellState) {
        this.stateProperty.set(state)
    }

    constructor()

    open fun getDisplayValue(): String {
        return stateProperty.get().getDisplayValue(this)
    }

    open fun isHidden() : Boolean {
        return stateProperty.get().isHidden()
    }

    open fun isDobted() : Boolean {
        return stateProperty.get().isDoubted()
    }

    open fun isFlagged() : Boolean {
        return stateProperty.get().isFlagged()
    }

    open fun isExploded() : Boolean {
        return stateProperty.get().isExploded()
    }

    open fun isRevealed() : Boolean {
        return stateProperty.get().isRevealed()
    }

    open fun getStatusType(): CellStates {
        return stateProperty.get().getStatusType()
    }

    fun leftClick() {
        stateProperty.get().leftClick(this)
    }

    fun rightClick() {
        stateProperty.get().rightClick(this)
    }

    fun doubleLeftClick() {
        stateProperty.get().doubleLeftClick(this)
    }

    override fun toString(): String {
        return stateProperty.get().toString(this)
    }

    abstract fun clone(): Cell
}