package game;

public class Coordinate {
    int positionX;
    int positionY;


    public Coordinate(int x, int y) {
        positionX = x;
        positionY = y;

    }

    //  Creates a coordinate from a UCI move string
//  coordinate - The coordinate in string format (UCI)
    public Coordinate(String coordinate) {
        positionX = coordinate.toCharArray()[0] - 97;
        positionY = Integer.parseInt(coordinate.substring(1, 2)) - 1;

    }

    //Checks if a Coordinate is valid or not
    public boolean isValid() {
        return (positionX >= 0 && positionX < 8) && (positionY >= 0 && positionY < 8);
    }


    public int getX() {
        return positionX;
    }


    public int getY() {
        return positionY;
    }

    public String toString() {
        return positionX + "," + positionY;
    }


    public boolean equals(Coordinate coordinate) {
        return (positionX == coordinate.getX())
                && (positionY == coordinate.getY());
    }

    // This converts the coordinate to UCI chess notation
// return String The string representation of the square in UCI form
    public String getParsedCoordinate() {
        String parsedString;
        parsedString = (char) (positionX + 97) + Integer.toString(positionY + 1);
        return parsedString;
    }
}
