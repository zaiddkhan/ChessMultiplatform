package ui.game

import androidx.compose.runtime.Composable
import board.InitialPiecesPositions
import board.rememberBoard
import cafe.adriel.voyager.core.screen.Screen

data class GameScreenNav(
    val encodedPieces: String = InitialPiecesPositions
): Screen {

    @Composable
    override fun Content() {
        val board = rememberBoard( encodedPieces)

        GameScreen(
            board = board
        )
    }

}