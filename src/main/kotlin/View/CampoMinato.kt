package CampoMinato.View

import CampoMinato.Model.GameBoard
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.Scene
import javafx.stage.Stage

class CampoMinatoController {
    @FXML
    private lateinit var game_grid: GridPane

    @FXML
    private lateinit var button1: Button

    @FXML
    private lateinit var button2: Button

    @FXML
    fun initialize() {
        for (x in 0..9) {
            for (y in 0..9) {
                val button = Button()
                button.text = "0"

                button.prefWidth = 50.0
                button.prefHeight = 50.0


                button.onMouseClicked = EventHandler {
                    if (it.isSecondaryButtonDown) {
//                        if (it.clickCount == 2) {
//                            GameBoard.getCell(x, y).doubleClick()
//                        } else {
//                            GameBoard.getCell(x, y).rightClick()
//                        }
                        GameBoard.getCell(x, y).rightClick()
                    } else {
                        GameBoard.getCell(x, y).leftClick()
                    }

                }

                GameBoard.getCell(x, y).stateProperty.addListener { _, _, newValue ->
                    button.text = newValue.toString()
                }

                game_grid.add(button, x, y)
            }
        }
    }

    fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/campo_minato.fxml"))
        val scene = Scene(fxmlLoader.load<GridPane>())
        stage.scene = scene
        stage.show()
    }
}