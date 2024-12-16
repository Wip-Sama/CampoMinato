package CampoMinato.Model

abstract class CellStatus() {
    open fun isHidden() : Boolean {
        return false
    }

    open fun isDoubted() : Boolean {
        return false
    }

    open fun isFlagged() : Boolean {
        return false
    }

    open fun isRevealed() : Boolean {
        return false
    }

    abstract fun leftClick(cell: Cell)

    abstract fun rightClick(cell: Cell)

    abstract fun getStatusType(): Statuses

    //By default this should do nothing
    open fun click(cell: Cell) {}
}