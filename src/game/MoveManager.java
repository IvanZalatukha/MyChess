package game;

import java.util.List;

public class MoveManager {
    //return The parsed string of the moves
    public static String parse(List<Move> moveList) {
        StringBuilder parsedMoves = new StringBuilder();
        for (Move move : moveList) {
            if (parseMove(move).length() != 1) {
                parsedMoves.append(" ").append(parseMove(move));
            } else {
                parsedMoves.append(parseMove(move));
            }
        }
        return parsedMoves.toString();
    }

    //return The parsed string of one move
    private static String parseMove(Move move) {
        if (move.getInitCoordinate().equals(move.getFinalCoordinate())) {
            return move.getPiece().getType().toString();
        }
        return move.getInitCoordinate().getParsedCoordinate() + move.getFinalCoordinate().getParsedCoordinate();
    }
}
