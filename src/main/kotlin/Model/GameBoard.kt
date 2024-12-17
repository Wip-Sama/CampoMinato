package CampoMinato.Model

import CampoMinato.Model.Cell_Statuses.Hidden
import CampoMinato.Model.Cell_Statuses.Revealed
import CampoMinato.Model.State.Game_states.Ongoing
import javafx.stage.FileChooser
import java.io.File

//Prototype
object GameBoard {
    private val mine_filter = FileChooser.ExtensionFilter("Mine Files", "*.minesweep")
    var bombs = 10
    var rows = 10
    var columns = 10
    var flags = 0
    private var gameState = Ongoing
    private lateinit var cells : Array<Array<Cell>>

    init {
        newGame()
    }

    private fun setBombs() {
        val randomList = (0..<(rows*columns)).shuffled().take(bombs)
        for (i in randomList) {
            val x = i / 10
            val y = i % 10
            cells[x][y] = CellFactory.setTypeBomb().build()
        }
    }

    fun isOngoing() : Boolean {
        return gameState.isOngoing()
    }

    fun isEnded() : Boolean {
        return gameState.isEnded()
    }

    fun countFlags() {
        flags = 0
        for (x in 0 until rows) {
            for (y in 0 until columns) {
                if (cells[x][y].isFlagged()) {
                    flags++
                }
            }
        }
    }

    fun newGame() {
        cells = Array(10) { Array(10) { CellFactory.build() } }
        setBombs()
    }

    fun won(): Boolean {
        for (x in 0 until rows) {
            for (y in 0 until columns) {
                if (cells[x][y].isHidden() && !cells[x][y].isBomb) {
                    return false
                }
            }
        }
        return true
    }

    fun lose(): Boolean {
        for (x in 0 until rows) {
            for (y in 0 until columns) {
                if (cells[x][y].isExploded()) {
                    return true
                }
            }
        }
        return false
    }

    fun getCellPosition(cell: Cell): Pair<Int, Int> {
        for (x in 0..<rows) {
            for (y in 0..<columns) {
                if (cells[x][y] == cell) {
                    return Pair(x, y)
                }
            }
        }
        return Pair(-1, -1)
    }

    fun searchBombs(x: Int, y: Int): Int {
        var bombs = 0
        for (i in -1..1) {
            for (j in -1..1) {
                if (x + i in 0 until rows && y + j in 0 until columns) {
                    if (cells[x + i][y + j].isBomb) {
                        bombs++
                    }
                }
            }
        }
        return bombs
    }

    fun revealNeighbors(x: Int, y: Int) {
        for (i in -1..1) {
            for (j in -1..1) {
                if (x + i in 0 until rows && y + j in 0 until columns) {
                    if (cells[x + i][y + j].isHidden()) {
                        cells[x + i][y + j].leftClick()
                    }
                }
            }
        }
    }

    fun discoverBombs(cell: Cell) {
        val (x, y) = getCellPosition(cell)
        if (searchBombs(x, y) == 0) {
            for (i in -1..1) {
                for (j in -1..1) {
                    if (x + i in 0 until rows && y + j in 0 until columns) {
                        if (cells[x + i][y + j].isHidden()) {
                            cells[x + i][y + j].leftClick()
                        }
                    }
                }
            }
        }
    }

    fun revealAllBombs() {
        for (x in 0 until rows) {
            for (y in 0 until columns) {
                if (cells[x][y].isBomb) {
                    cells[x][y].leftClick()
                }
            }
        }
    }

    fun revealAllCells() {
        for (x in 0 until rows) {
            for (y in 0 until columns) {
                cells[x][y].stateProperty.set(Revealed)
            }
        }
    }

    fun hideAllCells() {
        for (x in 0 until rows) {
            for (y in 0 until columns) {
                cells[x][y].stateProperty.set(Hidden)
            }
        }
    }

    fun getCell(x: Int, y: Int): Cell {
        return cells[x][y]
    }

    fun loadGame() {
        val fc = FileChooser()
        fc.extensionFilters.add(mine_filter)
        val selectedFile: File? = fc.showOpenDialog(null)
        selectedFile?.let { it ->
            val data = it.readLines()[0]
            data.split(",").let { (r, c, bombs, time, grid) ->
                this.rows = r.toInt()
                this.columns = c.toInt()
                this.bombs = bombs.toInt()
                //Set time
                cells = Array(rows) { Array(columns) { CellFactory.build() } }
                for (x in 0 until rows) {
                    for (y in 0 until columns) {
                        val s = grid[x * columns + y].toString()

                        if (s.uppercase() == s)
                            CellFactory.setTypeBomb()
                        else
                            CellFactory.setTypeEmpty()

                        when (s.uppercase()) {
                            "D" -> cells[y][x] = CellFactory.setStatusDoubted().build()
                            "E" -> cells[y][x] = CellFactory.setStatusExploded().build()
                            "F" -> cells[y][x] = CellFactory.setStatusFlagged().build()
                            "H" -> cells[y][x] = CellFactory.setStatusHidden().build()
                            "R" -> cells[y][x] = CellFactory.setStatusRevealed().build()
                        }
                    }
                }
            }
        }
    }

    fun saveGame() {
        val fileChooser = FileChooser()
        fileChooser.extensionFilters.add(mine_filter)
        val file: File? = fileChooser.showSaveDialog(null)
        file?.let {
            val data = "${rows},${columns},${bombs},${System.currentTimeMillis()},${cells.joinToString("") { it.joinToString("") { cell -> cell.toString() } }}"
            it.writeText(data)
        }
    }
}