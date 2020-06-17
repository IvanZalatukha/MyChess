package game;

import pieces.Piece;

public class Move {

    private final Coordinate initCoordinate;
    private final Coordinate finalCoordinate;
    private final Piece piece;


    public Move(Coordinate initCoordinate, Coordinate finalCoordinate, Piece piece) {
        this(initCoordinate, finalCoordinate, piece, null);
    }

    public Move(Coordinate initCoordinate, Coordinate finalCoordinate, Piece piece, Square captureSquare) {
        this.initCoordinate = initCoordinate;
        this.finalCoordinate = finalCoordinate;
        this.piece = piece;
    }


    public Coordinate getInitCoordinate() {
        return initCoordinate;
    }

    public Coordinate getFinalCoordinate() {
        return finalCoordinate;
    }

    public Piece getPiece() {
        return piece;
    }

}


