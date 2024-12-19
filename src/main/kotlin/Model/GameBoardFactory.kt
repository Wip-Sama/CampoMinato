package CampoMinato.Model

object GameBoardFactory {
    private var rows = 10
    private var columns = 10
    private var bombs = 10
    private var safeCell = Pair(-1, -1)
    private var grid = ""

    fun setRows(rows: Int) : GameBoardFactory {
        this.rows = rows
        return this
    }

    fun setColumns(columns: Int) : GameBoardFactory {
        this.columns = columns
        return this
    }

    fun setBombs(bombs: Int) : GameBoardFactory {
        this.bombs = bombs
        return this
    }

    fun setSafeCell(safeCell: Pair<Int, Int>) : GameBoardFactory {
        this.safeCell = safeCell
        return this
    }

    fun setGrid(grid: String) : GameBoardFactory {
        this.grid = grid
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