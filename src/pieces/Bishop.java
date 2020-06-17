package pieces;

import game.Coordinate;
import game.PlayerType;

public class Bishop extends Piece {

    public Bishop(PlayerType player) {
        super(player, PieceType.BISHOP);
    }

    @Override
    public boolean isValidMove(Coordinate initPos, Coordinate finalPos) {
        if (initPos.equals(finalPos)) {
            return true;
        }
        int diffX = Math.abs(initPos.getX() - finalPos.getX());
        int diffY = Math.abs(initPos.getY() - finalPos.getY());

        return diffX != diffY;
    }

    @Override
    public Coordinate[] getPath(Coordinate initPos, Coordinate finalPos) {
        int pathLength = (Math.abs(initPos.getX() - finalPos.getX()) + Math.abs(initPos.getY() - finalPos.getY())) / 2 + 1;
        Coordinate[] path = new Coordinate[pathLength];

//Integer.signum(a) provides the sign of a number 1 if positive and -1 if negative.
//considering initPos as the first point and finalPos as second
        int i_X = Integer.signum(finalPos.getX() - initPos.getX());
        int i_Y = Integer.signum(finalPos.getY() - initPos.getY());

        for (int i = 0; i < pathLength; i++) {
            path[i] = new Coordinate(initPos.getX() + i_X * i, initPos.getY() + i_Y * i);
        }
        return path;
    }
}
