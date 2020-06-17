package pieces;

import game.Coordinate;
import game.PlayerType;

public class Rook extends Piece {

    public Rook(PlayerType player) {
        super(player, PieceType.ROOK);
    }

    @Override
    public boolean isValidMove(Coordinate initPos, Coordinate finalPos) {
        if (initPos.equals(finalPos)) {
            return true;
        }
        return initPos.getX() != finalPos.getX() && initPos.getY() != finalPos.getY();
    }

    @Override
    public Coordinate[] getPath(Coordinate initPos, Coordinate finalPos) {
        int pathLength = Math.abs(initPos.getX() - finalPos.getX()) + Math.abs(initPos.getY() - finalPos.getY()) + 1;
        Coordinate[] path = new Coordinate[pathLength];
        for (int i = 0; i < pathLength; i++) {
            if ((initPos.getX() == finalPos.getX())) {
                path[i] = new Coordinate(initPos.getX(), Math.min(initPos.getY(), finalPos.getY()) + i);
            } else {
                path[i] = new Coordinate(Math.min(initPos.getX(), finalPos.getX()) + i, initPos.getY());
            }
        }
        return path;
    }
}
