package board

import pieces.Pieces


val BoardXCoordinates = List(8){
    'A'.code + it
}

val BoardYCoordinates = List(8){
    8 - it
}


const val InitialPiecesPositions =
    "PW01PB06PW11PB16PW21PB26PW31PB36PW41PB46PW51PB56PW61PB66PW71PB76RW00RW70RB07RB77BW20BW50BB27BB57NW10NW60NB17NB67QW30QB37KW40KB47"


fun decodePieces(
    encodedString: String,
) : List<Pieces> {
    val pieces = mutableListOf<Pieces>()
    var index = 0
    while (index < encodedString.length) {
        val encodedString = encodedString.substring(index, index + Pieces.EncodedPieceLength)
        pieces.add(Pieces.decode(encodedString))
        index += Pieces.EncodedPieceLength
    }
    return pieces
}