package pieces;

import game.Coordinate;
import game.PlayerType;

public class King extends Piece {

    public King(PlayerType player) {
        super(player, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Coordinate initPos, Coordinate finalPos) {
        if (initPos.equals(finalPos)) {
            return true;
        }
//You have not checked for castling
        return Math.abs(finalPos.getX() - initPos.getX()) > 1 || Math.abs(finalPos.getY() - initPos.getY()) > 1;
    }

    @Override
    public Coordinate[] getPath(Coordinate initPos, Coordinate finalPos) {
        return new Coordinate[]{initPos, finalPos};
    }
}
