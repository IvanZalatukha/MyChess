package pieces;

import game.Coordinate;
import game.PlayerType;

public abstract class Piece {
    private final PieceType pieceType;
    private final PlayerType player;

    public Piece(PlayerType player, PieceType pieceType) {
        this.pieceType = pieceType;
        this.player = player;
    }

    public String toString() {
        return player.toString() + pieceType.toString();
    }

    public PlayerType getPlayer() {
        return player;
    }

    public PieceType getType() {
        return pieceType;
    }

    public abstract boolean isValidMove(Coordinate initPos, Coordinate finalPos);

    public abstract Coordinate[] getPath(Coordinate initPos, Coordinate finalPos);
}
