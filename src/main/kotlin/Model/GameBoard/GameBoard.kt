package CampoMinato.Model.GameBoard

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.CellFactory
import CampoMinato.Model.GameBoard.States.Ongoing
import CampoMinato.Model.Scoreboard.SessionHistory
import CampoMinato.Model.Scoreboard.Size
import CampoMinato.Model.SessionHistory.Container
import CampoMinato.Model.SessionHistory.Difficulty
import CampoMinato.Model.SessionHistory.Game
import javafx.beans.property.SimpleObjectProperty
import kotlin.properties.Delegates

//Prototype
class GameBoard {
    internal val gameStateProperty = SimpleObjectProperty<GameBoardState>(Ongoing)
    private var gameRegistred = false
    var cells: Array<Array<Cell>>
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
        println()
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
        val safeRange = (safeCell.first - 1..safeCell.first + 1).flatMap { x ->
            (safeCell.second - 1..safeCell.second + 1).map { y ->
                x * columns + y
            }
        }
        val randomList = (0 until (rows * columns)).shuffled().filter { it !in safeRange }.take(bombs)
        for (i in randomList) {
            val x = i / columns
            val y = i % columns
            cells[x][y] = CellFactory.setTypeBomb().build()
        }
    }

    fun gameBoardIterator(): GameBoardIterator {
        return GameBoardIterator(cells)
    }

    fun rangedGameBoardIterator(x: Int, y: Int, range: Int): RangedGameBoardIterator {
        return RangedGameBoardIterator(cells, Pair(x, y), range)
    }

    fun isOngoing(): Boolean {
        return gameStateProperty.get().isOngoing()
    }

    fun isEnded(): Boolean {
        return gameStateProperty.get().isEnded()
    }

    fun won(): Boolean {
        return gameStateProperty.get().won(this)
    }

    fun lose(): Boolean {
        return gameStateProperty.get().lose(this)
    }

    fun countFlags(): Int {
        return gameStateProperty.get().countFlags(this)
    }

    fun searchBombs(x: Int, y: Int): Int {
        return gameStateProperty.get().countNearbyBombs(this, x, y)
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

    private fun gridToString(): String {
        return cells.joinToString("") { it.joinToString("") { cell -> cell.toString() } }
    }

    override fun toString(): String {
        return "${rows},${columns},${bombs},${gridToString()}"
    }

    fun clone(): GameBoard {
        val newBoard = GameBoard(rows, columns, bombs, toString())
        newBoard.gameStateProperty.set(gameStateProperty.get())
        return newBoard
    }

    fun updateSessionHistory() {
        if (gameRegistred) return
        var size: Size? = null
        var diff: Difficulty? = null
        for (sizes in SessionHistory.children) {
            if (sizes is Container && sizes.getSessionBoard().startsWith("size: $columns"+"x$rows")) {
                size = sizes as Size
                for (dif in sizes.children) {
                    if (dif is Container && dif.getSessionBoard().startsWith("diff: $bombs")) {
                        diff = dif as Difficulty
                        break
                    }
                }
                break
            }
        }
        if (size == null) {
            size = Size(columns.toString() + "x" + rows.toString())
            SessionHistory.add(size)
        }
        if (diff == null) {
            diff = Difficulty(bombs.toString(), size)
            size.add(diff)
        }
        diff.add(Game(if (won()) "won" else "lost"))
        gameRegistred = true
    }
}