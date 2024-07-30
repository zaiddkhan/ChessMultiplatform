package Pieces

import androidx.compose.runtime.TestOnly
import androidx.compose.ui.unit.IntOffset
import board.BoardXCoordinates
import board.BoardYCoordinates
import pieces.Pawn
import pieces.Pieces
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * 1. if is first move be can have 2 movements forward.
 * 2. if not first move can be 1 movement forward
 * 3. We can capture enemis in diagonal foward
 */
class PawnTest {

    private val demoWhitePiece : Pieces =
        Pawn(
            color = Pieces.Color.White,
            position = IntOffset(
                BoardXCoordinates.first(),
                BoardYCoordinates.first()
            )
        )

    private val demoBlackPiece : Pieces =
        Pawn(
            color = Pieces.Color.Black,
            position = IntOffset(
                BoardXCoordinates.first(),
                BoardYCoordinates.first()
            )
        )

    @Test
    fun testMoveForward(){
        val pawn = Pawn(
            color = Pieces.Color.White,
            position = IntOffset(
                x = 'A'.code,
                y = 2
            )
        )
        val moves = pawn.getAvailableMoves(listOf(pawn))
        assertTrue(moves.isNotEmpty())
        assertEquals(
            IntOffset('A'.code,3),
            moves.first()
        )
    }
    @Test
    fun testSecondMoveForwardTrue(){
        val pawn = Pawn(
            color = Pieces.Color.White,
            position = IntOffset(
                x = 'A'.code,
                y = 2
            )
        )
        val moves = pawn.getAvailableMoves(listOf(pawn)).toList()
        assertEquals(2,moves.size)
        assertTrue (
            IntOffset('A'.code,3) in moves
        )
        assertTrue (
            IntOffset('A'.code,4) in moves
        )


    }

    @Test
    fun testSecondMoveForwardFalse(){
        val pawn = Pawn(
            color = Pieces.Color.White,
            position = IntOffset(
                x = 'A'.code,
                y = 3
            )
        )
        val moves = pawn.getAvailableMoves(listOf(pawn)).toList()
        assertEquals(1,moves.size)
        assertEquals(IntOffset(
            'A'.code,4
        ),moves.first())


    }

    @Test
    fun testNoPossibleMoves(){
        val pawn = Pawn(
            color = Pieces.Color.White,
            position = IntOffset(
                x = 'A'.code,
                y = 3
            )
        )
        demoBlackPiece.position =
            IntOffset(
                x = 'A'.code,
                y = 3
            )

        val pieces = listOf(
            pawn,
            demoBlackPiece
        )
        val moves = pawn.getAvailableMoves(pieces)
        assertTrue(moves.isEmpty())
    }


}