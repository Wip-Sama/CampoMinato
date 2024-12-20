package CampoMinato.Model.GameBoard

import CampoMinato.Model.Cell.Cell

class RangedGameBoardIterator() {
    private var cells: Array<Array<Cell>> = emptyArray()
    private var origin: Pair<Int, Int> = Pair(0, 0)
    private var offsetX = 0
    private var offsetY = 0
    private var range = 0

    constructor(cells: Array<Array<Cell>>, origin: Pair<Int, Int>, range: Int) : this() {
        this.cells = cells
        this.origin = origin
        this.range = range
        this.offsetX = -range
        this.offsetY = -range
    }

    fun hasNext(): Boolean {
        while (offsetX <= range) {
            val x = origin.first + offsetX
            val y = origin.second + offsetY
            if (x in cells.indices && y in cells[0].indices) {
                return true
            }
            offsetY++
            if (offsetY > range) {
                offsetY = -range
                offsetX++
            }
        }
        return false
    }

    fun next(): Cell {
        val x = origin.first + offsetX
        val y = origin.second + offsetY
        offsetY++
        if (offsetY > range) {
            offsetY = -range
            offsetX++
        }
        return cells[x][y]
    }
}