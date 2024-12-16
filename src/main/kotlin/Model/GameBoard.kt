package CampoMinato.Model

//Prototype
object GameBoard {
    var bombs = 10

    private var cells : Array<Array<Cell>> = Array(10) { Array(10) { CellFactory.build() } }

    fun getCell(x: Int, y: Int): Cell {
        return cells[x][y]
    }
}