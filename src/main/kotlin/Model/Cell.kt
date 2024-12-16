package CampoMinato.Model

import CampoMinato.Model.Cell_Statuses.Hidden
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleObjectProperty

enum class Types {
    BOMB,
    EMPTY
}

enum class Statuses {
    HIDDEN,
    REVEALED,
    FLAGGED,
    DOUBTED,
    EXPLODED
}

//Flyweight (non più, se cambia lo stato mi sevre un proxi e non ho voglia di crearlo)
abstract class Cell {
    abstract val isBomb: Boolean

    constructor(state: CellStatus) {
        this.stateProperty.set(state)
    }

    constructor()

    val stateProperty = SimpleObjectProperty<CellStatus>(Hidden())

    open fun isHidden() : Boolean {
        return stateProperty.get().isHidden()
    }

    open fun isDobted() : Boolean {
        return stateProperty.get().isDoubted()
    }

    open fun isFlagged() : Boolean {
        return stateProperty.get().isFlagged()
    }

    open fun isRevealed() : Boolean {
        return stateProperty.get().isRevealed()
    }

    fun getStatusType(): Statuses {
        return stateProperty.get().getStatusType()
    }

    fun leftClick() {
        stateProperty.get().leftClick(this)
    }

    fun rightClick() {
        stateProperty.get().rightClick(this)
    }

    abstract fun clone(): Cell
}