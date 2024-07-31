package pieces

import androidx.compose.ui.unit.IntOffset
import pieces.dsl.*

fun Pieces.buildPieceMovement(
    pieces: List<Pieces>,
    block : PieceMovementBuilder.() -> Unit
) : Set<IntOffset> {
    val builder = PieceMovementBuilder(this,pieces)
     builder.block()
    return builder.build()
}
class PieceMovementBuilder(
    private val piece: Pieces,
    private val pieces: List<Pieces>
) {

    private val moves = mutableListOf<IntOffset>()

    fun straightMoves(
        maXMovements : Int = 7,
        canCapture : Boolean = true,
        captureOnly : Boolean = false
    ) {
        StraightMovement.entries.forEach { straightMove ->

                straightMoves(
                    movement = straightMove,
                    maXMovements = maXMovements,
                    captureOnly = captureOnly,
                    canCapture = canCapture
                )

        }

    }
    fun straightMoves(
        movement: StraightMovement,
        maXMovements : Int = 7,
        canCapture : Boolean = true,
        captureOnly : Boolean = false
    ) {
        moves.addAll(
            piece.getStraightMoves(
                pieces,
                movement = movement,
                maxMovements = maXMovements,
                captureOnly = captureOnly,
                canCapture = canCapture
            )
        )
    }

    fun diagonalMoves(
        maXMovements : Int = 7,
        canCapture : Boolean = true,
        captureOnly : Boolean = false
    ) {
        DiagonalMovement.entries.forEach { diagonalMovement ->

            diagonalMoves(
                movement = diagonalMovement,
                maXMovements = maXMovements,
                canCapture = canCapture,
                captureOnly = captureOnly,
            )

        }
    }


    fun getLMoves() {
        moves.addAll(
            piece.getLMoves(pieces)
        )
    }
    fun diagonalMoves(
        movement: DiagonalMovement,
        maXMovements : Int = 7,
        canCapture : Boolean = true,
        captureOnly : Boolean = false
    ) {
        moves.addAll(
            piece.getDiagonalMoves(
                pieces,
                movement = movement,
                maxMovements = maXMovements,
                captureOnly = captureOnly,
                canCapture = canCapture
            )
        )
    }


    fun build() : Set<IntOffset> = moves.toSet()
}