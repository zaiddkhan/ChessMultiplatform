package pieces

import androidx.compose.ui.unit.IntOffset
import chessmultiplatform.composeapp.generated.resources.*
import chessmultiplatform.composeapp.generated.resources.Res
import chessmultiplatform.composeapp.generated.resources.king_white
import org.jetbrains.compose.resources.DrawableResource

class King(
    override val color : Pieces.Color,
    override var position : IntOffset
) : Pieces{

    override val drawable: DrawableResource
         = if(color.isWhite) Res.drawable.king_white else Res.drawable.king_black

    override fun getAvailableMoves(pieces: List<Pieces>): Set<IntOffset> {
        return buildPieceMovement(pieces){
            straightMoves(
                maXMovements = 1
            )
            diagonalMoves(
                maXMovements = 1
            )
        }
    }

    override val type: Char
        get() = Type

    companion object{
        const val Type = 'K'
    }
}