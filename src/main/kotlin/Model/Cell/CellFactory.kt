package CampoMinato.Model.Cell

import CampoMinato.Model.Cell.Enums.CellStates
import CampoMinato.Model.Cell.Enums.CellTypes
import CampoMinato.Model.Cell.States.*

object CellFactory {
    private var new_cell = Array(2) { CellTypes.EMPTY.ordinal; CellStates.HIDDEN.ordinal}
    private var useProxy = true
    private var lastId: Int = 0

    fun setTypeBomb(): CellFactory {
        new_cell[0] = CellTypes.BOMB.ordinal
        return CellFactory
    }

    fun setTypeEmpty(): CellFactory {
        new_cell[0] = CellTypes.EMPTY.ordinal
        return CellFactory
    }

    fun setTypeFromLetter(letter: Char): CellFactory {
        if (letter.isUpperCase()) {
            setTypeBomb()
        } else {
            setTypeEmpty()
        }
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

    fun setStatusFromLetter(letter: Char): CellFactory {
        when (letter.uppercase()) {
            "D" -> setStatusDoubted()
            "E" -> setStatusExploded()
            "F" -> setStatusFlagged()
            "H" -> setStatusHidden()
            "R" -> setStatusRevealed()
        }
        return CellFactory
    }

    fun useProxy(): CellFactory {
        useProxy = true
        return CellFactory
    }

    fun avoidProxy(): CellFactory {
        useProxy = false
        return CellFactory
    }

    fun build(): Cell {
        lastId++
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
            CellTypes.EMPTY.ordinal -> CellImplementation(lastId,false, state)
            CellTypes.BOMB.ordinal -> CellImplementation(lastId,true, state)
            else -> CellImplementation(lastId,false, state)
        }

        new_cell = Array(2) { CellTypes.EMPTY.ordinal; CellStates.HIDDEN.ordinal}

        if (useProxy)
            return CellProxy(cell)
        return cell
    }
}

