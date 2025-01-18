package Model.GameBoard

import CampoMinato.Model.Cell.Cell
import CampoMinato.Model.Cell.CellFactory
import CampoMinato.Model.GameBoard.RangedGameBoardIterator
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.properties.Delegates

class RangedGameGameIteratorTest {
	private lateinit var cells: Array<Array<Cell>>
	private lateinit var origin: Pair<Int, Int>
	private var range by Delegates.notNull<Int>()
	private lateinit var rangedCellIterator: RangedGameBoardIterator

	@BeforeEach
	fun setUp() {
		cells = Array(10) { Array(10) { CellFactory.build() } }
		origin = Pair(3, 3)
		range = 1
		rangedCellIterator = RangedGameBoardIterator(cells, origin, range)
	}

	@Test
	fun hasNext() {
		assertTrue(rangedCellIterator.hasNext())

		for (i in 0 until (1+range*2)*(1+range*2)) {
			assertTrue(rangedCellIterator.hasNext())
			rangedCellIterator.next()
			print("i: $i\n")
		}

		assertFalse(rangedCellIterator.hasNext())
	}

	@Test
	fun next() {
		for (i in 0 until (1+range*2)*(1+range*2)) {
			rangedCellIterator.next()
		}
		assertFalse(rangedCellIterator.hasNext())
	}

	@Test
	fun `check top left corner`() {
		rangedCellIterator = RangedGameBoardIterator(cells, Pair(0,0), range)
		var count = 0
		while (rangedCellIterator.hasNext()) {
			val cell = rangedCellIterator.next()
			count++
		}
		assertEquals(4, count)
	}

	@Test
	fun `check top right corner`() {
		rangedCellIterator = RangedGameBoardIterator(cells, Pair(0,9), range)
		var count = 0
		while (rangedCellIterator.hasNext()) {
			val cell = rangedCellIterator.next()
			count++
		}
		assertEquals(4, count)
	}

	@Test
	fun `check bottom left corner`() {
		rangedCellIterator = RangedGameBoardIterator(cells, Pair(9,0), range)
		var count = 0
		while (rangedCellIterator.hasNext()) {
			val cell = rangedCellIterator.next()
			count++
		}
		assertEquals(4, count)
	}

	@Test
	fun `check bottom right corner`() {
		rangedCellIterator = RangedGameBoardIterator(cells, Pair(9,9), range)
		var count = 0
		while (rangedCellIterator.hasNext()) {
			val cell = rangedCellIterator.next()
			count++
		}
		assertEquals(4, count)
	}

	@Test
	fun `check top`() {
		rangedCellIterator = RangedGameBoardIterator(cells, Pair(0,5), range)
		var count = 0
		while (rangedCellIterator.hasNext()) {
			val cell = rangedCellIterator.next()
			count++
		}
		assertEquals(6, count)
	}

	@Test
	fun `check bottom`() {
		rangedCellIterator = RangedGameBoardIterator(cells, Pair(9,5), range)
		var count = 0
		while (rangedCellIterator.hasNext()) {
			val cell = rangedCellIterator.next()
			count++
		}
		assertEquals(6, count)
	}

	@Test
	fun `check left`() {
		rangedCellIterator = RangedGameBoardIterator(cells, Pair(5,0), range)
		var count = 0
		while (rangedCellIterator.hasNext()) {
			val cell = rangedCellIterator.next()
			count++
		}
		assertEquals(6, count)
	}

	@Test
	fun `check right`() {
		rangedCellIterator = RangedGameBoardIterator(cells, Pair(5,9), range)
		var count = 0
		while (rangedCellIterator.hasNext()) {
			val cell = rangedCellIterator.next()
			count++
		}
		assertEquals(6, count)
	}
}