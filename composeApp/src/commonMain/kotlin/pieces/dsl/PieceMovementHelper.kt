package pieces.dsl

import androidx.compose.ui.unit.IntOffset
import board.BoardXCoordinates
import board.BoardYCoordinates
import pieces.Pieces

fun Pieces.getMoves(
    pieces: List<Pieces>,
    maxMovements : Int,
    getPosition : (Int) -> IntOffset,
    canCapture : Boolean,
    captureOnly : Boolean
) : Set<IntOffset> {
    val moves = mutableSetOf<IntOffset>()
    for( i in 1..maxMovements) {
        val targetOffset = getPosition(i)

        if(targetOffset.x !in BoardXCoordinates || targetOffset.y !in  BoardYCoordinates)
            break

        val targetPiece = pieces.find {
            it.position == targetOffset
        }
        if(targetPiece != null){
            if(targetPiece.color != this.color && canCapture)
                moves.add(targetOffset)
            break;
        }else if(canCapture){
            break;
        }
        else{
            moves.add(targetOffset)

        }
    }
    return moves

}

fun Pieces.getLMoves(
    pieces: List<Pieces>,
) : MutableSet<IntOffset> {
    val moves = mutableSetOf<IntOffset>()
    val offsets = listOf(
        IntOffset(-1,-2),
        IntOffset(1,-2),
        IntOffset(-2,-1),
        IntOffset(2,-1),
        IntOffset(-2,1),
        IntOffset(2,1),
        IntOffset(-1,2),
        IntOffset(1,2),
    )
    for(offset in offsets){
        val targetPosition = position + offset
        if(targetPosition.x !in BoardXCoordinates || targetPosition.y !in BoardYCoordinates) {
            continue
        }
        val targetPiece = pieces.find {
            it.position == targetPosition
        }
        if(targetPiece == null || targetPiece.color != this.color) {
            moves.add(targetPosition)
        }
    }
    return moves
}