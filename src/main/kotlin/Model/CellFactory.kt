package CampoMinato.Model

import CampoMinato.Model.Cell_Statuses.Doubted
import CampoMinato.Model.Cell_Statuses.Flagged
import CampoMinato.Model.Cell_Statuses.Hidden
import CampoMinato.Model.Cell_Statuses.Revealed
import CampoMinato.Model.Cell_Types.BombCell
import CampoMinato.Model.Cell_Types.EmptyCell

//Singleton
object CellFactory {
    //here an array of 2 int
//    private var initialized_cells = HashMap<Array<Int>, Cell>()

    private var new_cell = Array(2) {Types.EMPTY.ordinal; Statuses.HIDDEN.ordinal}

    fun setTypeBomb(): CellFactory {
        new_cell[0] = Types.BOMB.ordinal
        return CellFactory
    }

    fun setTypeEmpty(): CellFactory {
        new_cell[0] = Types.EMPTY.ordinal
        return CellFactory
    }

    fun setStatusHidden(): CellFactory {
        new_cell[1] = Statuses.HIDDEN.ordinal
        return CellFactory
    }

    fun setStatusDoubted(): CellFactory {
        new_cell[1] = Statuses.DOUBTED.ordinal
        return CellFactory
    }

    fun setStatusRevealed(): CellFactory {
        new_cell[1] = Statuses.REVEALED.ordinal
        return CellFactory
    }

    fun setStatusFlagged(): CellFactory {
        new_cell[1] = Statuses.FLAGGED.ordinal
        return CellFactory
    }

    fun build(): Cell {
//        Il problema è che se una cella varia lo stato devo clonarla per evitare che
//        cambia lo stato ovunque e riutilizzata quella cella quindi mi si incvalida la "cache"
//        if (initialized_cells.containsKey(new_cell)) {
//            return initialized_cells[new_cell]!!
//        } else {
//            val state: CellStatus = when (new_cell[1]) {
//                Statuses.HIDDEN.ordinal -> Hidden()
//                Statuses.DOUBTED.ordinal -> Doubted()
//                Statuses.REVEALED.ordinal -> Revealed()
//                Statuses.FLAGGED.ordinal -> Flagged()
//                else -> {
//                    Hidden()
//                }
//            }
//
//            val cell = when (new_cell[0]) {
//                Types.BOMB.ordinal -> BombCell(state)
//                Types.EMPTY.ordinal -> EmptyCell(state)
//                else -> EmptyCell(state)
//            }
//
//            initialized_cells[new_cell] = cell
//            new_cell = Array(2) {Types.EMPTY.ordinal; Statuses.HIDDEN.ordinal}
//            return cell
//        }

        val state: CellStatus = when (new_cell[1]) {
            Statuses.HIDDEN.ordinal -> Hidden()
            Statuses.DOUBTED.ordinal -> Doubted()
            Statuses.REVEALED.ordinal -> Revealed()
            Statuses.FLAGGED.ordinal -> Flagged()
            else -> {
                Hidden()
            }
        }

        val cell = when (new_cell[0]) {
            Types.BOMB.ordinal -> BombCell(state)
            Types.EMPTY.ordinal -> EmptyCell(state)
            else -> EmptyCell(state)
        }

        new_cell = Array(2) {Types.EMPTY.ordinal; Statuses.HIDDEN.ordinal}

        return cell
    }

}

