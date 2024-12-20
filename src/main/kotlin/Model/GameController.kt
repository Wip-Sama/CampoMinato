package CampoMinato.Model

import CampoMinato.Model.GameBoard.GameBoard
import CampoMinato.Model.GameBoard.GameBoardFactory
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.stage.FileChooser
import java.io.File

object GameController {
    private val mine_filter = FileChooser.ExtensionFilter("Mine Files", "*.minesweep")
    private var gameBoard = SimpleObjectProperty(
        GameBoardFactory
            .setRows(10)
            .setColumns(10)
            .setBombs(10)
            .build()
    )

    fun getGameBoard(): ReadOnlyProperty<GameBoard> {
        return gameBoard
    }

    fun newGame() {
        gameBoard.set(GameBoardFactory.build())
    }

    fun newGame(rows: Int, columns: Int, bombs: Int, safeCell: Pair<Int, Int>) {
        gameBoard.set(
            GameBoardFactory
                .setRows(rows)
                .setColumns(columns)
                .setBombs(bombs)
                .setSafeCell(safeCell)
                .build()
        )
    }

    fun newGame(rows: Int, columns: Int, bombs: Int) {
        gameBoard.set(
            GameBoardFactory
                .setRows(rows)
                .setColumns(columns)
                .setBombs(bombs)
                .build()
        )
    }

    fun loadGame() {
        val fc = FileChooser()
        fc.extensionFilters.add(mine_filter)
        val selectedFile: File? = fc.showOpenDialog(null)
        selectedFile?.let {
            val data = it.readLines()[0]
            data.split(",").let { (rows, columns, bombs, grid) ->
                gameBoard.set(
                    GameBoardFactory
                        .setRows(rows.toInt())
                        .setColumns(columns.toInt())
                        .setBombs(bombs.toInt())
                        .setGrid(grid)
                        .build()
                )
            }
        }
    }

    fun saveGame() {
        val fileChooser = FileChooser()
        fileChooser.extensionFilters.add(mine_filter)
        val file: File? = fileChooser.showSaveDialog(null)
        file?.writeText(gameBoard.value.toString())
    }
}