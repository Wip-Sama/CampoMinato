package CampoMinato

import CampoMinato.View.CampoMinatoController
import javafx.application.Application
import javafx.stage.Stage

class CampoMinato : Application() {
    override fun start(primaryStage: Stage) {
        CampoMinatoController().start(primaryStage)
    }
}

fun main() {
    Application.launch(CampoMinato::class.java)
}