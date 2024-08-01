package pieces

import androidx.compose.ui.unit.IntOffset
import board.BoardXCoordinates
import board.BoardYCoordinates
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.skia.Color

interface Pieces {

    val color : Color

    enum class Color {
        White,
        Black;

        val isWhite get() = this == White

        val isBlack get() = this == Black
    }

    val type : Char

    val drawable : DrawableResource

    var position : IntOffset

    fun getAvailableMoves(pieces : List<Pieces>) : Set<IntOffset>

    fun encode() : String {
        val colorCode = color.name.first()
        return StringBuilder()
            .append(type)
            .append(colorCode)
            .append(position.x - BoardXCoordinates.min())
            .append(position.y - BoardYCoordinates.min())
            .toString()

    }

    companion object {
        fun decode(encoded: String) : Pieces {
            val (type,color,x,y) = encoded.toCharArray()
            val pieceColor = Color.entries.find {
                it.name.first() == color
            } ?: throw IllegalArgumentException("Unknown color")

            val position = IntOffset(
               x = x.digitToInt() + BoardXCoordinates.min(),
                y = y.digitToInt() + BoardYCoordinates.min()
            )

            return when(type) {
                Pawn.Type ->
                    Pawn(pieceColor,position)
                King.Type ->
                    King(pieceColor,position)
                Queen.Type ->
                    Queen(pieceColor,position)
                Knights.Type ->
                    Knights(pieceColor,position)
                Rook.Type ->
                    Rook(pieceColor,position)
                Bishop.Type ->
                    Bishop(pieceColor,position)
                else -> throw IllegalArgumentException("Unknown color")
            }


        }
        const val EncodedPieceLength = 4
    }
}