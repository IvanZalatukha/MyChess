package gui;

import game.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

import pieces.PieceType;


public class GameGUI {
    private final BoardManager boardManager;
    private JButton lastSelection = null;
    private JButton[][] allButtons = null;

    public static void main(String[] args) {
        new GameGUI();
    }

    public GameGUI() {
        boardManager = new BoardManager();
        initialize();

    }

    private void initialize() {
        JFrame guiFrame = new JFrame();
        guiFrame.setMinimumSize(new Dimension(700, 700));
        guiFrame.setTitle("Chess");
        guiFrame.setSize(800, 600);
// make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        guiFrame.setLayout(new BorderLayout());

        ChessWindow window = new ChessWindow(boardManager.getBoard().getSquares());

        allButtons = window.getButtons();
        guiFrame.add(window);
        guiFrame.pack();
        window.setVisible(true);
        guiFrame.setVisible(true);

    }

    class MyActionListener implements ActionListener {


        public void updateBoard() {
            Square[][] squares = boardManager.getBoard().getSquares();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    JButton button;
                    Square square;
                    if (boardManager.getCurrentPlayer() == PlayerType.BLACK) {
                        button = allButtons[col][row];
                        square = squares[row][col];
                    } else {
                        button = allButtons[col][row];
                        square = squares[row][7 - col];
                    }

// This is necessary to set the background of the buttons.
                    int offset = 1;
                    if (boardManager.getCurrentPlayer() == PlayerType.WHITE) {
                        offset = 0;
                    }
                    if ((row + col + offset) % 2 == 0) {
                        button.setBackground(Color.white);
                    } else {
                        button.setBackground(new Color(114, 84, 67));
                    }
                    // Places peices if there the square are occupied.
                    if (square.isOccupied()) {
                        String playerString;
                        String pieceString;
                        if (square.getPiece().getPlayer() == PlayerType.WHITE) {
                            playerString = "white";
                        } else {
                            playerString = "black";
                        }
                        if (square.getPiece().getType() == PieceType.KING) {
                            pieceString = "King";
                        } else if (square.getPiece().getType() == PieceType.PAWN) {
                            pieceString = "Pawn";
                        } else if (square.getPiece().getType() == PieceType.ROOK) {
                            pieceString = "Rook";
                        } else if (square.getPiece().getType() == PieceType.KNIGHT) {
                            pieceString = "Knight";
                        } else if (square.getPiece().getType() == PieceType.BISHOP) {
                            pieceString = "Bishop";
                        } else {
                            pieceString = "Queen";
                        }

                        Image image = null;
                        try {
                            image = ImageIO.read(getClass().getResource(
                                    "/" + playerString + pieceString + ".png"));
                        } catch (IOException ignored) {
                        }
                        assert image != null;
                        button.setIcon(new ImageIcon(image));

                    } else {
                        button.setIcon(null);
                    }
                }
            }
        }

        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            Point rv = new Point();
            int selectionX = button.getLocation(rv).x / 80;
            int selectionY = button.getLocation(rv).y / 80;
            if (boardManager.getCurrentPlayer() == PlayerType.WHITE) {
                selectionY = 7 - selectionY;
            }
            Coordinate currentCoordinate = new Coordinate(selectionX, selectionY);

            boolean moved = false;
            if (lastSelection != null) {
                selectionX = lastSelection.getLocation(rv).x / 80;
                selectionY = lastSelection.getLocation(rv).y / 80;
                if (boardManager.getCurrentPlayer() == PlayerType.WHITE) {
                    selectionY = 7 - selectionY;
                }
                Coordinate lastCoordinate = new Coordinate(selectionX, selectionY);
                moved = boardManager.move(lastCoordinate, currentCoordinate);
// This will create promotion window when the pawn reaches the last row
                if (moved) {
                    if (boardManager.isValidPromotion(boardManager.getBoard().getSquare(currentCoordinate))) {
                        JFrame promotionFrame = new JFrame();
                        promotionFrame.setMinimumSize(new Dimension(200, 200));
                        promotionFrame.setTitle("PiecePromotion");
                        promotionFrame.setSize(250, 100);
                        JButton bishop = new JButton();
                        JButton queen = new JButton();
                        JButton rook = new JButton();
                        JButton knight = new JButton();
                        JPanel panel = new JPanel();
                        panel.add(bishop);
                        panel.add(queen);
                        panel.add(rook);
                        panel.add(knight);
                        try {
//This will assign a corresponding icon to each button and using the lambda function make the promotion
                            bishop.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/blackBishop.png"))));
                            bishop.setPreferredSize(new Dimension(80, 80));
                            bishop.addActionListener(e1 -> {
                                boardManager.promote(boardManager.getBoard().getSquare(currentCoordinate), PieceType.BISHOP);
                                promotionFrame.dispose();
                                updateBoard();
                            });
                            queen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/blackQueen.png"))));
                            queen.setPreferredSize(new Dimension(80, 80));
                            queen.addActionListener(e12 -> {
                                boardManager.promote(boardManager.getBoard().getSquare(currentCoordinate), PieceType.QUEEN);
                                promotionFrame.dispose();
                                updateBoard();
                            });
                            rook.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/blackRook.png"))));
                            rook.setPreferredSize(new Dimension(80, 80));
                            rook.addActionListener(e13 -> {
                                boardManager.promote(boardManager.getBoard().getSquare(currentCoordinate), PieceType.ROOK);
                                promotionFrame.dispose();
                                updateBoard();
                            });
                            knight.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/blackKnight.png"))));
                            knight.setPreferredSize(new Dimension(80, 80));
                            knight.addActionListener(e14 -> {
                                boardManager.promote(boardManager.getBoard().getSquare(currentCoordinate), PieceType.KNIGHT);
                                promotionFrame.dispose();
                                updateBoard();
                            });

                        } catch (IOException d) {
                            d.printStackTrace();
                        }
                        promotionFrame.add(panel);
                        promotionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        promotionFrame.setLocationRelativeTo(null);
                        promotionFrame.pack();
                        promotionFrame.setVisible(true);
                    }
                    lastSelection = null;
                    updateBoard();
                }
            }
// highlight possible  squares for moving
            if (!moved) {
                updateBoard();
                highlightSquaresWhite(boardManager.getValidMoves(currentCoordinate));
                lastSelection = button;
            }
        }
    }


    public void highlightSquaresWhite(Square[] squares) {
        ArrayList<Square> notOccupiedSquares = new ArrayList<>();
        for (Square s : squares) {
            if (!s.isOccupied()) {
                notOccupiedSquares.add(s);
            }
        }
        notOccupiedSquares.toArray();
        for (Square c : notOccupiedSquares) {
            try {
                if (boardManager.getCurrentPlayer() == PlayerType.WHITE) {
                    allButtons[7 - c.getCoordinate().getY()][c.getCoordinate().getX()].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/greenCircle.png"))));
                } else {
                    allButtons[c.getCoordinate().getY()][c.getCoordinate().getX()].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/greenCircle.png"))));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class ChessWindow extends JPanel {

        private final JButton[][] allButtons = new JButton[8][8];

        public JButton[][] getButtons() {
            return allButtons;
        }

        public ChessWindow(Square[][] squares) {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    gbc.gridx = row;
                    gbc.gridy = col;
                    JButton button = new JButton();
                    allButtons[col][row] = button;

                    button.setBorderPainted(false);
                    button.setPreferredSize(new Dimension(80, 80));

                    Square square;
                    if (boardManager.getCurrentPlayer() == PlayerType.BLACK) {
                        square = squares[row][col];
                    } else {
                        square = squares[row][7 - col];
                    }
                    if (square.isOccupied()) {
                        String playerString;
                        String pieceString;
                        if (square.getPiece().getPlayer() == PlayerType.WHITE) {
                            playerString = "white";
                        } else {
                            playerString = "black";
                        }
                        if (square.getPiece().getType() == PieceType.KING) {
                            pieceString = "King";
                        } else if (square.getPiece().getType() == PieceType.PAWN) {
                            pieceString = "Pawn";
                        } else if (square.getPiece().getType() == PieceType.ROOK) {
                            pieceString = "Rook";
                        } else if (square.getPiece().getType() == PieceType.KNIGHT) {
                            pieceString = "Knight";
                        } else if (square.getPiece().getType() == PieceType.BISHOP) {
                            pieceString = "Bishop";
                        } else {
                            pieceString = "Queen";
                        }
                        Image image = null;
                        try {
                            image = ImageIO.read(getClass().getResource("/" + playerString + pieceString + ".png"));
                        } catch (IOException ignored) {
                        }
                        assert image != null;
                        button.setIcon(new ImageIcon(image));

                    } else {
                        button.setIcon(null);
                    }

                    if ((row + col) % 2 == 0) {
                        button.setBackground(Color.white);
                    } else {
                        button.setBackground(new Color(114, 84, 67));
                    }
                    MyActionListener actionListener = new MyActionListener();
                    button.addActionListener(actionListener);
                    add(button, gbc);
                }
            }
        }
    }
}






