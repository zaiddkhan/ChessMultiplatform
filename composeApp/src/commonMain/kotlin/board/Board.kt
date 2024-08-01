package board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntOffset
import com.russhwolf.settings.set
import kotlinx.datetime.Clock
import pieces.Pieces
import settings.boardSettings

@Composable
fun rememberBoard(
    encodedPiecess: String = InitialPiecesPositions,
): Board =
    remember {
        Board(encodedPiecess = encodedPiecess)
    }

@Immutable
class Board(
    encodedPiecess: String = InitialPiecesPositions,
) {
    private val _Piecess = mutableStateListOf<Pieces>()
    val Piecess get() = _Piecess.toList()

    init {
        _Piecess.addAll(
            decodePieces( encodedPiecess)
        )
    }

    var selectedPieces by mutableStateOf<Pieces?>(null)
        private set

    var selectedPiecesMoves by mutableStateOf(emptySet<IntOffset>())
        private set

    var moveIncrement by mutableIntStateOf(0)
        private set

    var playerTurn by mutableStateOf(Pieces.Color.White)

    /**
     * User events
     */

    fun selectPieces(Pieces: Pieces) {
        if (Pieces.color != playerTurn)
            return

        if (Pieces == selectedPieces) {
            clearSelection()
        } else {
            selectedPieces = Pieces
            selectedPiecesMoves = Pieces.getAvailableMoves( Piecess)
        }
    }

    fun moveSelectedPieces(x: Int, y: Int) {
        selectedPieces?.let { Pieces ->
            if (!isAvailableMove(x = x, y = y))
                return

            if (Pieces.color != playerTurn)
                return

            movePieces(
                Pieces = Pieces,
                position = IntOffset(x, y)
            )

            clearSelection()

            switchPlayerTurn()

            moveIncrement++
        }
    }

    /**
     * Public Methods
     */

    fun getPieces(x: Int, y: Int): Pieces? =
        _Piecess.find { it.position.x == x && it.position.y == y }

    fun isAvailableMove(x: Int, y: Int): Boolean =
        selectedPiecesMoves.any { it.x == x && it.y == y }

    fun save() {
        val encodedBoard = encode()
        val millis = Clock.System.now().toEpochMilliseconds()

        boardSettings[BoardKeyPrefix + millis] = encodedBoard
    }

    /**
     * Private Methods
     */

    private fun movePieces(
        Pieces: Pieces,
        position: IntOffset
    ) {
        val targetPieces = Piecess.find { it.position == position }

        if (targetPieces != null)
            removePieces(targetPieces)

        Pieces.position = position
    }

    private fun removePieces(Pieces: Pieces) {
        _Piecess.remove(Pieces)
    }


    private fun clearSelection() {
        selectedPieces = null
        selectedPiecesMoves = emptySet()
    }

    private fun switchPlayerTurn() {
        playerTurn =
            if (playerTurn.isWhite)
                Pieces.Color.Black
            else
                Pieces.Color.White
    }

    private fun encode(): String {
        return Piecess.joinToString(separator = "") { it.encode() }
    }

    companion object {
        const val BoardKeyPrefix = "board_"
    }
}

@Composable
fun Board.rememberPiecesAt(x: Int, y: Int): Pieces? =
    remember(x, y, moveIncrement) {
        getPieces(
            x = x,
            y = y,
        )
    }

@Composable
fun Board.rememberIsAvailableMove(x: Int, y: Int): Boolean =
    remember(x, y, selectedPiecesMoves) {
        isAvailableMove(
            x = x,
            y = y,
        )
    }