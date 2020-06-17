package game;

import pieces.Piece;

public class Square {

    private final Coordinate coordinate;
    private Piece piece;


    public Square(Coordinate coordinate, Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }

    public Square(Coordinate coordinate) {
        this(coordinate, null);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }


    public boolean equals(Square square) {
        return square.getCoordinate().equals(coordinate);
    }

    public boolean isOccupied() {
        return piece != null;
    }


    //Removes any piece that is currently in the square
    public void releasePiece() {
        piece = null;
    }
}
