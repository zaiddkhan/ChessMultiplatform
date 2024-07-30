package pieces

import androidx.compose.ui.unit.IntOffset

enum class StraightMovement {
    Up, Down, Left, Right
}

fun Pieces.getStraightMoves(
    pieces: List<Pieces>,
    movement: StraightMovement,
    maxMovements: Int = 7,
    canCapture: Boolean = true
): Set<IntOffset> {
    return getMoves(
        pieces = pieces,
        getPosition = {
            when(movement) {
                StraightMovement.Up -> {
                    IntOffset(
                        x = position.x,
                        y = position.y + it
                    )
                }
                StraightMovement.Down -> {
                    IntOffset(
                        x = position.x,
                        y = position.y - it
                    )
                }
                StraightMovement.Left -> {
                    IntOffset(
                        x = position.x - it,
                        y = position.y
                    )
                }
                StraightMovement.Right -> {
                    IntOffset(
                        x = position.x + it,
                        y = position.y
                    )
                }
            }

        },
        maxMovements = maxMovements,
        canCapture = canCapture
    )
}
fun Pieces.getMoves(
    pieces: List<Pieces>,
    maxMovements : Int ,
    getPosition : (Int) -> IntOffset,
    canCapture : Boolean
) : Set<IntOffset> {
    val moves = mutableSetOf<IntOffset>()
    for( i in 1..maxMovements) {
        val targetOffset = getPosition(i)
        val targetPiece = pieces.find {
            it.position == targetOffset
        }
        if(targetPiece != null){
            if(targetPiece.color != this.color && canCapture)
                moves.add(targetOffset)
            break;
        }else{
            moves.add(targetOffset)

        }
    }
    return moves

}