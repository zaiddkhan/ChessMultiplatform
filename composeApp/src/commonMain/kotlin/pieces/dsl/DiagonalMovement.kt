package pieces.dsl

import androidx.compose.ui.unit.IntOffset
import pieces.Pieces

enum class DiagonalMovement {
    UpLeft, UpRight, DownLeft, DownRight
}

fun Pieces.getDiagonalMoves(
    pieces: List<Pieces>,
    movement: DiagonalMovement,
    maxMovements: Int = 7,
    canCapture: Boolean = true,
    captureOnly: Boolean = false
): Set<IntOffset> {
    return getMoves(
        pieces = pieces,
        getPosition = {
            when(movement) {
                DiagonalMovement.UpLeft -> {
                    IntOffset(
                        x = position.x - it,
                        y = position.y + it
                    )
                }
                DiagonalMovement.UpRight -> {
                    IntOffset(
                        x = position.x + it,
                        y = position.y + it
                    )
                }
                DiagonalMovement.DownLeft -> {
                    IntOffset(
                        x = position.x - it,
                        y = position.y - it
                    )
                }
                DiagonalMovement.DownRight -> {
                    IntOffset(
                        x = position.x + it,
                        y = position.y  - it
                    )
                }
            }

        },
        maxMovements = maxMovements,
        canCapture = canCapture,
        captureOnly = captureOnly
    )
}
