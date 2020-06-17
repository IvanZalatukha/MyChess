package pieces;

import game.Coordinate;
import game.PlayerType;

public class Pawn extends Piece {

    public Pawn(PlayerType player) {
        super(player, PieceType.PAWN);
    }

    @Override
    public boolean isValidMove(Coordinate initPos, Coordinate finalPos) {
        if (initPos.equals(finalPos)) {
            return true;
        }

// This is for normal pawn moves.
        if (Math.abs(initPos.getY() - finalPos.getY()) == 1 &&
                Math.abs(initPos.getX() - finalPos.getX()) == 0) {
            // White can only move forward
            if (this.getPlayer() == PlayerType.WHITE) {
                if (initPos.getY() < finalPos.getY()) {
                    return false;
                }
            }
// Black can only move backward in a sense.
            if (this.getPlayer() == PlayerType.BLACK) {
                if (initPos.getY() > finalPos.getY()) {
                    return false;
                }
            }
        }

// This is for first pawn move.
        if (Math.abs(initPos.getY() - finalPos.getY()) == 2 &&
                Math.abs(initPos.getX() - finalPos.getX()) == 0 &&
                (initPos.getY() == 1 || initPos.getY() == 6)) {

// White can only move forward
            if (this.getPlayer() == PlayerType.WHITE) {
                if (initPos.getY() < finalPos.getY()) {
                    return false;
                }
            }
// Black can only move backward in a sense.
            if (this.getPlayer() == PlayerType.BLACK) {
                return initPos.getY() <= finalPos.getY();
            }

        }

// This if for normal pawn captures.
// this is for Enpassant.

        return true;
    }

    @Override
    public Coordinate[] getPath(Coordinate initPos, Coordinate finalPos) {
//This is for pawn captures
        if (initPos.getX() != finalPos.getX()) {
            return new Coordinate[]{initPos, finalPos};
        }
//This is for normal pawn moves and first pawn moves.
        int pathLength = Math.abs(initPos.getY() - finalPos.getY()) + 1;
        Coordinate[] path = new Coordinate[pathLength];

        for (int i = 0; i < pathLength; i++) {
            path[i] = new Coordinate(initPos.getX(), Math.min(initPos.getY(), finalPos.getY()) + i);
        }

        return path;
    }
}
