package CampoMinato.Model.GameBoard

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.CellFactory
import CampoMinato.Model.GameBoard.Game_states.Ongoing
import javafx.beans.property.SimpleObjectProperty
import kotlin.properties.Delegates

//Prototype
class GameBoard {
    internal val gameStateProperty = SimpleObjectProperty<GameBoardState>(Ongoing)
    var cells : Array<Array<Cell>>
        private set
    var rows by Delegates.notNull<Int>()
        private set
    var columns by Delegates.notNull<Int>()
        private set
    var bombs by Delegates.notNull<Int>()
        private set

    constructor(rows: Int, columns: Int, bombs: Int, safeCell: Pair<Int, Int>) {
        this.rows = rows
        this.columns = columns
        this.bombs = bombs
        cells = Array(rows) { Array(columns) { CellFactory.build() } }
        setBombs(safeCell)
    }

    constructor(rows: Int, columns: Int, bombs: Int, grid: String) {
        this.rows = rows
        this.columns = columns
        this.bombs = bombs
        cells = Array(rows) { x ->
            Array(columns) { y ->
                val char = grid[x * columns + y]
                CellFactory.setTypeFromLetter(char)
                CellFactory.setStatusFromLetter(char)
                CellFactory.build()
            }
        }
    }

    private fun setBombs() {
        val randomList = (0..<(rows * columns)).shuffled().take(bombs)
        for (i in randomList) {
            val x = i / columns
            val y = i % columns
            cells[x][y] = CellFactory.setTypeBomb().build()
        }
    }

    private fun setBombs(safeCell: Pair<Int, Int>) {
        val randomList = (0..<(rows * columns)).shuffled().filter { it != safeCell.first * columns + safeCell.second }.take(bombs)
        for (i in randomList) {
            val x = i / columns
            val y = i % columns
            cells[x][y] = CellFactory.setTypeBomb().build()
        }
    }

    fun isOngoing() : Boolean {
        return gameStateProperty.get().isOngoing()
    }

    fun isEnded() : Boolean {
        return gameStateProperty.get().isEnded()
    }

    fun won(): Boolean {
        return gameStateProperty.get().won(this)
    }

    fun lose(): Boolean {
        return gameStateProperty.get().lose(this)
    }

    fun countFlags() : Int {
        return gameStateProperty.get().countFlags(this)
    }

    fun searchBombs(x: Int, y: Int): Int {
        return gameStateProperty.get().searchBombs(this, x, y)
    }

    fun revealNeighbors(x: Int, y: Int) {
        gameStateProperty.get().revealNeighbors(this, x, y)
    }

    fun revealEmptyCells(cell: Cell) {
        gameStateProperty.get().revealEmptyCells(this, cell)
    }

    fun getCellPosition(cell: Cell): Pair<Int, Int> {
        return gameStateProperty.get().getCellPosition(this, cell)
    }

    fun revealAllBombs() {
        gameStateProperty.get().revealAllBombs(this)
    }

    fun revealAllCells() {
        gameStateProperty.get().revealAllCells(this)
    }

    fun hideAllCells() {
        gameStateProperty.get().hideAllCells(this)
    }

    private fun gridToString() : String {
        return cells.joinToString("") { it.joinToString("") { cell -> cell.toString() } }
    }

    override fun toString() : String {
        return "${rows},${columns},${bombs},${gridToString()}"
    }

    fun clone() : GameBoard {
        val newBoard = GameBoard(rows, columns, bombs, toString())
        newBoard.gameStateProperty.set(gameStateProperty.get())
        return newBoard
    }
}