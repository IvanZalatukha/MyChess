package pieces;

import game.Coordinate;
import game.PlayerType;

public class Knight extends Piece {

    public Knight(PlayerType player) {
        super(player, PieceType.KNIGHT);
    }

    @Override
    public boolean isValidMove(Coordinate initPos, Coordinate finalPos) {
        if (initPos.equals(finalPos)) {
            return true;
        }
        int diffX = Math.abs(initPos.getX() - finalPos.getX());
        int diffY = Math.abs(initPos.getY() - finalPos.getY());
        return (diffX + diffY) != 3 || diffX == 0 || diffY == 0;
    }

    @Override
    public Coordinate[] getPath(Coordinate initPos, Coordinate finalPos) {
//The knight can jump over pieces.
        return new Coordinate[]{initPos, finalPos};
    }

}
