package pieces

import androidx.compose.ui.unit.IntOffset
import chessmultiplatform.composeapp.generated.resources.Res
import chessmultiplatform.composeapp.generated.resources.soldier_black
import chessmultiplatform.composeapp.generated.resources.soldier_white
import org.jetbrains.compose.resources.DrawableResource
import pieces.dsl.DiagonalMovement
import pieces.dsl.StraightMovement
import pieces.dsl.getDiagonalMoves
import pieces.dsl.getStraightMoves

class Pawn (
    override val color : Pieces.Color,
    override var position : IntOffset
) : Pieces {

    override val drawable: DrawableResource =
        if(color.isWhite)
            Res.drawable.soldier_white
        else Res.drawable.soldier_black


    companion object{
        const val Type = 'P'
    }

    override val type: Char
        get() = Type

    override fun getAvailableMoves(pieces: List<Pieces>): Set<IntOffset> {

        val isFirstMove = position.y == 2 && color.isWhite ||
                position.y == 7 && color.isBlack

        return buildPieceMovement(
            pieces
        ){
            straightMoves(
                movement = if (color.isWhite) StraightMovement.Up else StraightMovement.Down,
                maXMovements = if (isFirstMove) 2 else 1,
                canCapture = false,
            )

            diagonalMoves(
                movement = if (color.isWhite) DiagonalMovement.UpRight else DiagonalMovement.DownRight,
                maXMovements = 1,
                captureOnly = true,
            )

            diagonalMoves(
                movement = if (color.isWhite) DiagonalMovement.UpLeft else DiagonalMovement.DownLeft,
                maXMovements = 1,
                captureOnly = true,
            )
        }

    }
}