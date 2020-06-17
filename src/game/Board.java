package game;

import pieces.*;

public class Board {

    private final Square[][] squares = new Square[8][8];

    public Board() {
        setSquares();
        setWhitePieces();
        setBlackPieces();
    }

    public void resetBoard() {
        setSquares();
        setWhitePieces();
        setBlackPieces();
    }


    private void setSquares() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares[x][y] = new Square(new Coordinate(x, y));
            }
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    private void setWhitePieces() {
        squares[2][0].setPiece(new Bishop(PlayerType.WHITE));
        squares[5][0].setPiece(new Bishop(PlayerType.WHITE));
        squares[1][0].setPiece(new Knight(PlayerType.WHITE));
        squares[6][0].setPiece(new Knight(PlayerType.WHITE));
        squares[0][0].setPiece(new Rook(PlayerType.WHITE));
        squares[7][0].setPiece(new Rook(PlayerType.WHITE));
        squares[3][0].setPiece(new Queen(PlayerType.WHITE));
        squares[4][0].setPiece(new King(PlayerType.WHITE));
        squares[0][1].setPiece(new Pawn(PlayerType.WHITE));
        squares[1][1].setPiece(new Pawn(PlayerType.WHITE));
        squares[2][1].setPiece(new Pawn(PlayerType.WHITE));
        squares[3][1].setPiece(new Pawn(PlayerType.WHITE));
        squares[4][1].setPiece(new Pawn(PlayerType.WHITE));
        squares[5][1].setPiece(new Pawn(PlayerType.WHITE));
        squares[6][1].setPiece(new Pawn(PlayerType.WHITE));
        squares[7][1].setPiece(new Pawn(PlayerType.WHITE));
    }

    private void setBlackPieces() {
        squares[2][7].setPiece(new Bishop(PlayerType.BLACK));
        squares[5][7].setPiece(new Bishop(PlayerType.BLACK));
        squares[1][7].setPiece(new Knight(PlayerType.BLACK));
        squares[6][7].setPiece(new Knight(PlayerType.BLACK));
        squares[0][7].setPiece(new Rook(PlayerType.BLACK));
        squares[7][7].setPiece(new Rook(PlayerType.BLACK));
        squares[3][7].setPiece(new Queen(PlayerType.BLACK));
        squares[4][7].setPiece(new King(PlayerType.BLACK));
        squares[0][6].setPiece(new Pawn(PlayerType.BLACK));
        squares[1][6].setPiece(new Pawn(PlayerType.BLACK));
        squares[2][6].setPiece(new Pawn(PlayerType.BLACK));
        squares[3][6].setPiece(new Pawn(PlayerType.BLACK));
        squares[4][6].setPiece(new Pawn(PlayerType.BLACK));
        squares[5][6].setPiece(new Pawn(PlayerType.BLACK));
        squares[6][6].setPiece(new Pawn(PlayerType.BLACK));
        squares[7][6].setPiece(new Pawn(PlayerType.BLACK));
    }

    public Square getSquare(Coordinate coordinate) {
        Square result = null;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (squares[x][y].getCoordinate().equals(coordinate)) {
                    result = squares[x][y];
                }
            }
        }
        return result;
    }

    public void makeMove(Coordinate initCoordinate, Coordinate finalCoordinate) {
        makeMove(getSquare(initCoordinate), getSquare(finalCoordinate));
    }

    public void capturePiece(Square square) {
        if (square.isOccupied()) {
            square.releasePiece();
        }
    }

    public void makeMove(Square initSquare, Square finalSquare) {

        if (finalSquare.isOccupied()) {
            capturePiece(finalSquare);
        }
        //Make the move
        finalSquare.setPiece(initSquare.getPiece());
        initSquare.releasePiece();
    }


}
