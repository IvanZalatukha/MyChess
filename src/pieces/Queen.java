package pieces;

import game.Coordinate;
import game.PlayerType;

public class Queen extends Piece {

    public Queen(PlayerType player) {
        super(player, PieceType.QUEEN);
    }

    @Override
    public boolean isValidMove(Coordinate initPos, Coordinate finalPos) {
        if (initPos.equals(finalPos)) {
            return true;
        }

        //This is the bishop move.
        int diffX = Math.abs(initPos.getX() - finalPos.getX());
        int diffY = Math.abs(initPos.getY() - finalPos.getY());
        if (diffX == diffY) {
            return false;
        }

        //This is the rook move.
        return initPos.getX() != finalPos.getX() && initPos.getY() != finalPos.getY();
    }

    @Override
    public Coordinate[] getPath(Coordinate initPos, Coordinate finalPos) {
        Coordinate[] path;
        //If it is a rook move
        if (initPos.getX() == finalPos.getX() || initPos.getY() == finalPos.getY()) {
            int pathLength = Math.abs(initPos.getX() - finalPos.getX()) + Math.abs(initPos.getY() - finalPos.getY()) + 1;
            path = new Coordinate[pathLength];
            for (int i = 0; i < pathLength; i++) {
                if ((initPos.getX() == finalPos.getX())) {
                    path[i] = new Coordinate(initPos.getX(), Math.min(initPos.getY(), finalPos.getY()) + i);
                } else {
                    path[i] = new Coordinate(Math.min(initPos.getX(), finalPos.getX()) + i, initPos.getY());
                }
            }
        } else {
            //If it a bishop move.
            int pathLength = (Math.abs(initPos.getX() - finalPos.getX()) + Math.abs(initPos.getY() - finalPos.getY())) / 2 + 1;
            path = new Coordinate[pathLength];

            //Integer.signum(a) provides the sign of a number 1 if positive and -1 if negative.
            //In this case i am considering initPos as the first point and finalPos as second
            int i_X = Integer.signum(finalPos.getX() - initPos.getX());
            int i_Y = Integer.signum(finalPos.getY() - initPos.getY());

            for (int i = 0; i < pathLength; i++) {
                path[i] = new Coordinate(initPos.getX() + i_X * i, initPos.getY() + i_Y * i);
            }
        }
        return path;
    }
}
