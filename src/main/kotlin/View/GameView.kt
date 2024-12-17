package CampoMinato.View

import CampoMinato.Model.GameBoard
import javafx.animation.PauseTransition
import javafx.css.PseudoClass
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import javafx.util.Duration

class CampoMinatoController {
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

    private fun updateAllButtons() {
        for (x in 0..<GameBoard.rows) {
            for (y in 0..<GameBoard.columns) {
                val button = game_grid.children
                    .filterIsInstance<Button>()
                    .first { GridPane.getRowIndex(it) == y && GridPane.getColumnIndex(it) == x }

                val cell = GameBoard.getCell(x, y)
                val bombs = GameBoard.searchBombs(x, y)

                button.text = cell.getDisplayValue()
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("warning"), bombs in 3..5 && cell.isRevealed())
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("danger"), bombs >= 6 && cell.isRevealed())
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("disabled"), cell.isRevealed())

                cell.stateProperty.addListener { _, _, _ ->
                    button.text = cell.getDisplayValue()
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("warning"), bombs in 3..5 && cell.isRevealed())
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("danger"), bombs >= 6 && cell.isRevealed())
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("disabled"), cell.isRevealed())
                }
            }
        }
        updateStatus()
    }

    private fun updateStatus() {
        GameBoard.countFlags()
        score_label.text = "Score: ${GameBoard.flags}/${GameBoard.bombs}"
//        time_label.text = "Time: ${GameBoard.time}"
        status_image.image = when {
            GameBoard.lose() -> ImageView("/images/lose.png").image
            GameBoard.won() -> ImageView("/images/win.png").image
            GameBoard.flags>GameBoard.bombs -> ImageView("/images/too_many.png").image
            GameBoard.flags==GameBoard.bombs -> ImageView("/images/near_win.png").image
            else -> ImageView("/images/nothing.png").image
        }
    }

    @FXML
    fun initialize() {
        // Initialize the game board
        for (x in 0..<GameBoard.rows) {
            for (y in 0..<GameBoard.columns) {
                val button = Button()
                button.isFocusTraversable = false
                button.text = ""
                button.setPrefSize(50.0, 50.0)

                val singlePressPause = PauseTransition(Duration.millis(100.0))

                singlePressPause.onFinished = EventHandler {
                    GameBoard.getCell(x, y).leftClick()
                    updateStatus()
                }

                button.onMouseClicked = EventHandler {
                    val cell = GameBoard.getCell(x, y)
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

                GameBoard.getCell(x, y).stateProperty.addListener { _, _, _ ->
                    val cell = GameBoard.getCell(x, y)
                    button.text = cell.getDisplayValue()
                    val bombs = GameBoard.searchBombs(x, y)
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("warning"), bombs in 3..5 && cell.isRevealed())
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("danger"), bombs >= 6 && cell.isRevealed())
                    button.pseudoClassStateChanged(PseudoClass.getPseudoClass("disabled"), cell.isRevealed())
                }

                game_grid.add(button, x, y)
            }
        }

        // Initialize the buttons
        new_game.onMouseClicked = EventHandler {
            GameBoard.newGame()
            GameBoard.hideAllCells()
            updateAllButtons()
        }

        load_game.onMouseClicked = EventHandler {
            GameBoard.loadGame()
            updateAllButtons()
        }

        save_game.onMouseClicked = EventHandler {
            GameBoard.saveGame()
        }

        updateStatus()
    }

    fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/campo_minato.fxml"))
        val scene = Scene(fxmlLoader.load<GridPane>())
        stage.scene = scene
        stage.show()
    }
}