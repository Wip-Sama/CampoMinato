package CampoMinato.Model.GameBoard

object GameBoardFactory {
    private var rows = 10
    private var columns = 10
    private var bombs = 10
    private var safeCell = Pair(-1, -1)
    private var grid = ""

    fun setRows(rows: Int): GameBoardFactory {
        GameBoardFactory.rows = rows
        return this
    }

    fun setColumns(columns: Int): GameBoardFactory {
        GameBoardFactory.columns = columns
        return this
    }

    fun setBombs(bombs: Int): GameBoardFactory {
        GameBoardFactory.bombs = bombs
        return this
    }

    fun setSafeCell(safeCell: Pair<Int, Int>): GameBoardFactory {
        GameBoardFactory.safeCell = safeCell
        return this
    }

    fun setGrid(grid: String): GameBoardFactory {
        GameBoardFactory.grid = grid
        return this
    }

    fun build(): GameBoard {
        if (grid != "") {
            val gm = GameBoard(rows, columns, bombs, grid)
            grid = ""
            return gm
        } else {
            if (safeCell.first == -1 && safeCell.second == -1) {
                safeCell = Pair((0 until rows).random(), (0 until columns).random())
            }
            val gm = GameBoard(rows, columns, bombs, safeCell.copy())
            safeCell = Pair(-1, -1)
            return gm
        }
    }

}