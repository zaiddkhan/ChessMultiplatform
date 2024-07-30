package pieces

import androidx.compose.ui.unit.IntOffset
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

    val drawable : DrawableResource

    var position : IntOffset

    fun getAvailableMoves(pieces : List<Pieces>) : Set<IntOffset>
}