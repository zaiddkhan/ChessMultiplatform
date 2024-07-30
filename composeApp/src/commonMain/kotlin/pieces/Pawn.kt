package pieces

import androidx.compose.ui.unit.IntOffset
import chessmultiplatform.composeapp.generated.resources.Res
import chessmultiplatform.composeapp.generated.resources.soldier_white
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.skia.PixelRef

class Pawn (
    override val color : Pieces.Color,
    override var position : IntOffset
) : Pieces {

    override val drawable: DrawableResource =
        if(color.isWhite)
            Res.drawable.soldier_white
            else Res.drawable.soldier_white

    override fun getAvailableMoves(pieces: List<Pieces>): Set<IntOffset> {

        val isFirstMove = position.y == 2 && color.isWhite ||
                position.y == 7 && color.isBlack
       return getStraightMoves(
            pieces = pieces,
            movement = if(color.isWhite) StraightMovement.Up else StraightMovement.Down,
            maxMovements = if(isFirstMove) 2 else 1,
           canCapture = false
        )
    }
}