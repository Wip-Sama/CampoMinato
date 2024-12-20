package CampoMinato.Model.GameBoard

import CampoMinato.Model.Cell.Cell

class GameBoardIterator(private val cells: Array<Array<Cell>>) {
    private var x = 0
    private var y = 0

    fun hasNext(): Boolean {
        return x < cells.size && y < cells[x].size
    }

    fun next(): Cell {
        val cell = cells[x][y]
        y++
        if (y >= cells[x].size) {
            x++
            y = 0
        }
        return cell
    }
}