package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Stockfish {

    private Process stockfish;
    private final BufferedWriter stockfishInput;
    private final BufferedReader stockfishOutput;
    private int stockfishLevel;

    public Stockfish(int level) {
        stockfishLevel = level;
        try {
            //TODO point the way to "stockfish"
            stockfish = Runtime.getRuntime().exec("point the way to stockfish");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stockfish != null) {
            stockfishInput = new BufferedWriter(new OutputStreamWriter(stockfish.getOutputStream()));
            stockfishOutput = new BufferedReader(new InputStreamReader(stockfish.getInputStream()));
        } else {
            throw new RuntimeException("Check file path for stockfish.exe");
        }
        try {
            stockfishInput.write("setoption name UCI_Chess960 value true\n");
            stockfishInput.write("setoption name Skill Level value " + stockfishLevel + "\n");
            stockfishInput.flush();
            stockfishOutput.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stockfish() {
        this(20);
    }


    public void setLevel(int level) {
        stockfishLevel = level;
        try {
            stockfishInput.write("setoption name Skill Level value " + stockfishLevel + "\n");
            stockfishInput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getBestMove(String position) {
        String output = "Stockfish api";
        try {
            stockfishInput.write("position startpos moves " + position + "\n");
            stockfishInput.write("go" + "\n");
            stockfishInput.flush();
            while (!output.startsWith("bestmove")) {
                output = stockfishOutput.readLine();
            }
            stockfishInput.write("stop" + "\n");
            if (output.equals("bestmove (none)")) {
                return "";
            }
            output = output.split(" ")[1];

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }


}
