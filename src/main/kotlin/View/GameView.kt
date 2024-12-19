package CampoMinato.View

import CampoMinato.Model.GameController
import javafx.animation.PauseTransition
import javafx.css.PseudoClass
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.stage.Stage
import javafx.util.Duration
import java.util.function.UnaryOperator

class CampoMinatoController {
    private lateinit var stage: Stage

    @FXML
    private lateinit var game_grid: GridPane

    @FXML
    private lateinit var new_game: Button

    @FXML
    private lateinit var load_game: Button

    @FXML
    private lateinit var save_game: Button

    @FXML
    private lateinit var score_label: Label

    @FXML
    private lateinit var status_image: ImageView

    private val contextMenu = createContextMenu()

    private fun updateAllButtons() {
        for (x in 0..<GameController.getGameBoard().value.rows) {
            for (y in 0..<GameController.getGameBoard().value.columns) {
                val button = game_grid.children
                    .filterIsInstance<Button>()
                    .first { GridPane.getRowIndex(it) == y && GridPane.getColumnIndex(it) == x }

                val cell = GameController.getGameBoard().value.cells[x][y]
                val bombs = GameController.getGameBoard().value.searchBombs(x, y)

                button.text = cell.getDisplayValue()
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("warning"), bombs in 3..4 && cell.isRevealed())
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("danger"), bombs >= 6 && cell.isRevealed())
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("disabled"), cell.isRevealed())

                cell.stateProperty.addListener { _, _, _ ->
                    button.text = cell.getDisplayValue()
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("warning"), bombs in 3..4 && cell.isRevealed())
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("danger"), bombs >= 6 && cell.isRevealed())
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("disabled"), cell.isRevealed())
                }
            }
        }
        updateStatus()
        resizeWindow()
    }

    private fun resizeWindow() {
        if (::game_grid.isInitialized) {
            val scene = game_grid.scene
            if (scene is Scene)
                scene.window.sizeToScene()
        }
    }

    private fun updateStatus() {
        val flags = GameController.getGameBoard().value.countFlags()
        score_label.text = "Score: ${flags}/${GameController.getGameBoard().value.bombs}"
//        time_label.text = "Time: ${GameBoard.time}"
        status_image.image = when {
            GameController.getGameBoard().value.lose() -> ImageView("/images/lose.png").image
            GameController.getGameBoard().value.won() -> ImageView("/images/win.png").image
            flags>GameController.getGameBoard().value.bombs -> ImageView("/images/too_many.png").image
            flags==GameController.getGameBoard().value.bombs -> ImageView("/images/near_win.png").image
            else -> ImageView("/images/nothing.png").image
        }
    }

    private fun createContextMenu(): ContextMenu {
        val loader = FXMLLoader(javaClass.getResource("/new_minefield.fxml"))
        val dialog = loader.load<BorderPane>()

        val rowsField = dialog.lookup("#rowsField") as TextField
        val columnsField = dialog.lookup("#columnsField") as TextField
        val bombsField = dialog.lookup("#bombsField") as TextField

        listOf(rowsField, columnsField).forEach { textField ->
            textField.focusedProperty().addListener { _, _, newValue ->
                if (!newValue) {
                    if (textField.text.isEmpty())
                        textField.text = "5"
                    else if (textField.text.toInt() < 5)
                        textField.text = "5"
                }
            }
            textField.textProperty().addListener { _, oldValue, newValue ->
                if (!newValue.matches(Regex("\\d*"))) {
                    textField.text = oldValue
                }
            }
        }

        bombsField.focusedProperty().addListener { _, _, newValue ->
            if (!newValue) {
                if (bombsField.text.isEmpty())
                    bombsField.text = "1"
                else if (bombsField.text.toInt() < 1)
                    bombsField.text = "1"
            }
        }
        bombsField.textProperty().addListener { _, oldValue, newValue ->
            if (!newValue.matches(Regex("\\d*"))) {
                bombsField.text = oldValue
            }
        }

        val contextMenu = ContextMenu()
        contextMenu.items.add(MenuItem().apply { graphic = dialog })
        return contextMenu
    }

    private fun generateButtonsGrid(rows: Int, columns: Int): GridPane {
        game_grid.children.clear()
        game_grid.columnConstraints.clear()
        game_grid.columnConstraints.setAll((0 until rows).map { ColumnConstraints(30.0) })
        game_grid.rowConstraints.clear()
        game_grid.rowConstraints.setAll((0 until columns).map { RowConstraints(30.0) })
        //adjust the number of rows and columns
        for (x in 0 until rows) {
            for (y in 0 until columns) {
                val button = Button()
                button.isFocusTraversable = false
                button.text = ""
                button.setPrefSize(30.0, 30.0)

                val singlePressPause = PauseTransition(Duration.millis(100.0))
                singlePressPause.onFinished = EventHandler {
                    GameController.getGameBoard().value.cells[x][y].leftClick()
                    updateStatus()
                }
                button.onMouseClicked = EventHandler {
                    val cell = GameController.getGameBoard().value.cells[x][y]
                    if (it.button.name == "SECONDARY") {
                        cell.rightClick()
                    } else {
                        if (it.clickCount == 1) {
                            singlePressPause.play()
                        }
                        if (it.clickCount == 2) {
                            singlePressPause.stop()
                            cell.doubleLeftClick()
                        }
                    }
                    updateStatus()
                }

                game_grid.add(button, x, y)
            }
        }
        return game_grid
    }

    @FXML
    fun initialize() {
        GameController.getGameBoard().addListener { _, _, _ ->
            generateButtonsGrid(GameController.getGameBoard().value.rows, GameController.getGameBoard().value.columns)
            updateAllButtons()
        }

        new_game.onMouseClicked = EventHandler {
            if (it.button.name == "SECONDARY") {
                val stage = new_game.scene.window as Stage
                val centerX = stage.x + stage.width / 2 - 150 / 2
                val centerY = stage.y + stage.height / 2 - 220 / 2
                contextMenu.show(new_game, centerX, centerY)
                contextMenu.onHidden = EventHandler {
                    //make rowsField and columnsField have number >= than 5 and bombsField have number always bigge>= than 1

                    val rows = (contextMenu.items[0].graphic.lookup("#rowsField") as TextField)
                    val columns = (contextMenu.items[0].graphic.lookup("#columnsField") as TextField)
                    val bombs = (contextMenu.items[0].graphic.lookup("#bombsField") as TextField)

                    if (rows.text.isNotEmpty() && columns.text.isNotEmpty() && bombs.text.isNotEmpty()) {
                        GameController.newGame(columns.text.toInt(), rows.text.toInt(), bombs.text.toInt())
                    }
                }
            } else {
                GameController.newGame()
            }
        }

        load_game.onMouseClicked = EventHandler {
            GameController.loadGame()
        }

        save_game.onMouseClicked = EventHandler {
            GameController.saveGame()
        }

        GameController.newGame()
        updateStatus()
    }

    fun start(stage: Stage) {
        this.stage = stage
        val fxmlLoader = FXMLLoader(javaClass.getResource("/campo_minato.fxml"))
        val scene = Scene(fxmlLoader.load<GridPane>())
        stage.scene = scene
        stage.show()
    }
}