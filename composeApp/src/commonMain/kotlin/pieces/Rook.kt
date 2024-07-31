package pieces

import androidx.compose.ui.unit.IntOffset
import chessmultiplatform.composeapp.generated.resources.*
import chessmultiplatform.composeapp.generated.resources.Res
import chessmultiplatform.composeapp.generated.resources.elephant_black
import org.jetbrains.compose.resources.DrawableResource

class Rook(
    override val color : Pieces.Color,
    override var position : IntOffset
) : Pieces{

    override val drawable: DrawableResource =
        if(color.isBlack) Res.drawable.elephant_black else Res.drawable.elephant_white

    override fun getAvailableMoves(pieces: List<Pieces>): Set<IntOffset> {
        return buildPieceMovement(pieces){
            straightMoves()
        }
    }
}