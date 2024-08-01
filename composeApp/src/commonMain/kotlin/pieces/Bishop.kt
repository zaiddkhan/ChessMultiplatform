package pieces

import androidx.compose.ui.unit.IntOffset
import chessmultiplatform.composeapp.generated.resources.Res
import chessmultiplatform.composeapp.generated.resources.camel_black
import chessmultiplatform.composeapp.generated.resources.camel_white
import org.jetbrains.compose.resources.DrawableResource

class Bishop(
    override val color : Pieces.Color,
    override var position : IntOffset
) : Pieces{

    override val drawable: DrawableResource
         = if(color.isWhite) Res.drawable.camel_white else Res.drawable.camel_black

    override fun getAvailableMoves(pieces: List<Pieces>): Set<IntOffset> {
        return buildPieceMovement(pieces){
            diagonalMoves()
        }
    }

    override val type: Char
        get() = Type

    companion object{
        const val Type = 'B'
    }
}