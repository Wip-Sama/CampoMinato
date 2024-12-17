package CampoMinato.Model

import CampoMinato.Model.Cell_Statuses.*
import CampoMinato.Model.Enums.CellStates
import CampoMinato.Model.Enums.CellTypes
import CampoMinato.Model.States.Cell_Types.BombCell
import CampoMinato.Model.States.Cell_Types.EmptyCell
import CampoMinato.Model.States.CellState

object CellFactory {
    private var new_cell = Array(2) { CellTypes.EMPTY.ordinal; CellStates.HIDDEN.ordinal}

    fun setTypeBomb(): CellFactory {
        new_cell[0] = CellTypes.BOMB.ordinal
        return CellFactory
    }

    fun setTypeEmpty(): CellFactory {
        new_cell[0] = CellTypes.EMPTY.ordinal
        return CellFactory
    }

    fun setStatusHidden(): CellFactory {
        new_cell[1] = CellStates.HIDDEN.ordinal
        return CellFactory
    }

    fun setStatusDoubted(): CellFactory {
        new_cell[1] = CellStates.DOUBTED.ordinal
        return CellFactory
    }

    fun setStatusRevealed(): CellFactory {
        new_cell[1] = CellStates.REVEALED.ordinal
        return CellFactory
    }

    fun setStatusFlagged(): CellFactory {
        new_cell[1] = CellStates.FLAGGED.ordinal
        return CellFactory
    }

    fun setStatusExploded(): CellFactory {
        new_cell[1] = CellStates.EXPLODED.ordinal
        return CellFactory
    }

    fun build(): Cell {
        val state: CellState = when (new_cell[1]) {
            CellStates.HIDDEN.ordinal -> Hidden
            CellStates.DOUBTED.ordinal -> Doubted
            CellStates.REVEALED.ordinal -> Revealed
            CellStates.FLAGGED.ordinal -> Flagged
            CellStates.EXPLODED.ordinal -> Exploded
            else -> {
                Hidden
            }
        }

        val cell = when (new_cell[0]) {
            CellTypes.EMPTY.ordinal -> EmptyCell(state)
            CellTypes.BOMB.ordinal -> BombCell(state)
            else -> EmptyCell(state)
        }

        new_cell = Array(2) { CellTypes.EMPTY.ordinal; CellStates.HIDDEN.ordinal}

        return cell
    }
}

