package pieces

import androidx.compose.ui.unit.IntOffset
import chessmultiplatform.composeapp.generated.resources.*
import chessmultiplatform.composeapp.generated.resources.Res
import chessmultiplatform.composeapp.generated.resources.queen_white
import org.jetbrains.compose.resources.DrawableResource

class Queen(
    override val color : Pieces.Color,
    override var position : IntOffset
) : Pieces{

    override val drawable: DrawableResource
         = if(color.isWhite) Res.drawable.queen_white else Res.drawable.queen_black

    override fun getAvailableMoves(pieces: List<Pieces>): Set<IntOffset> {
        return buildPieceMovement(pieces){
            straightMoves()
            diagonalMoves()
        }
    }
}