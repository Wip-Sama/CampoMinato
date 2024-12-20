package CampoMinato.Model.Cell

import CampoMinato.Model.Cell.Enums.CellStates
import CampoMinato.Model.Cell.States.Hidden
import javafx.beans.property.SimpleObjectProperty

class CellImplementation : Cell {
	private var isBomb: Boolean = false
	private val stateProperty: SimpleObjectProperty<CellState>
	private val cellId: Int

	constructor(cellId: Int, isBomb: Boolean, state: CellState) {
		this.cellId = cellId
		this.isBomb = isBomb
		stateProperty = SimpleObjectProperty(state)
	}

	constructor(cellId: Int) {
		this.cellId = cellId
		stateProperty = SimpleObjectProperty(Hidden)
	}

	override fun getDisplayValue(): String {
		return stateProperty.get().getDisplayValue(this)
	}

	override fun isHidden() : Boolean {
		return stateProperty.get().isHidden()
	}

	override fun isDoubted() : Boolean {
		return stateProperty.get().isDoubted()
	}

	override fun isFlagged() : Boolean {
		return stateProperty.get().isFlagged()
	}

	override fun isExploded() : Boolean {
		return stateProperty.get().isExploded()
	}

	override fun isRevealed() : Boolean {
		return stateProperty.get().isRevealed()
	}

	override fun getStatusType(): CellStates {
		return stateProperty.get().getStatusType()
	}

	override fun leftClick() {
		stateProperty.get().leftClick(this)
	}

	override fun rightClick() {
		stateProperty.get().rightClick(this)
	}

	override fun doubleLeftClick() {
		stateProperty.get().doubleLeftClick(this)
	}

	override fun getStateProperty(): SimpleObjectProperty<CellState> {
		return stateProperty
	}

	override fun isBomb(): Boolean {
		return isBomb
	}

	override fun getId(): Int {
		return cellId
	}

	override fun toString(): String {
		return stateProperty.get().toString(this)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Cell) return false

		// Compare relevant properties of the cells
		return 	this.cellId == other.getId() &&
				this.isBomb == other.isBomb() &&
				this.stateProperty.get().getStatusType() == other.getStatusType()
	}
}