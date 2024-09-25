import mancala.Game;
import mancala.State;
import player.Player;
import strategy.Heuristic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        run_AI_vs_AI_diff_heuristics();
//        run_AI_vs_AI_same_heuristics();
    }

    public static void run_AI_vs_AI_diff_heuristics() {
        Player p0 = new Player(0, "AI-1", false, true);
        Player p1 = new Player(1, "AI-2", false, false);

        int totalGames = 0;
        int draws = 0;
        int[] heuristic_wins = new int[Heuristic.num_heuristics + 1];

        int MIN_DEPTH = 6;
        int MAX_DEPTH = 10;

        int iterations = 2;

        String csvFile = "ai_vs_ai_simulation.csv";

        try (FileWriter fw = new FileWriter(csvFile);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("FirstPlayerHeuristic,FirstPlayerDepth,SecondPlayerHeuristic,SecondPlayerDepth,WinningPlayer,WinningHeuristic,WinningDepth");


            for (int i = 0; i < iterations; i++) {
                for (int h0 = 1; h0 <= Heuristic.num_heuristics; h0++) {
                    for (int d0 = MIN_DEPTH; d0 <= MAX_DEPTH; d0++) {
                        for (int h1 = 1; h1 <= Heuristic.num_heuristics; h1++) {
                            for (int d1 = MIN_DEPTH; d1 <= MAX_DEPTH; d1++) {
                                p0.heuristic_id = h0;
                                p0.depth = d0;
                                p1.heuristic_id = h1;
                                p1.depth = d1;

                                Game game = new Game(p0, p1);
                                int winningPlayerId = game.run(false);
                                totalGames++;

                                int winningHeuristic = (winningPlayerId == -1) ? -1 : game.players[winningPlayerId].heuristic_id;
                                int winningDepth = (winningPlayerId == -1) ? -1 : game.players[winningPlayerId].depth;

                                if (winningPlayerId == -1) {
                                    draws++;
                                } else {
                                    heuristic_wins[game.players[winningPlayerId].heuristic_id]++;
                                }

                                pw.println(h0 + "," + d0 + "," + h1 + "," + d1 + "," + (winningPlayerId+1) + "," + winningHeuristic + "," + winningDepth);

                            }
                        }
                    }
                }
            }

            System.out.println("\nTotal Games = " + totalGames + "\n");
            System.out.println(String.format("Games drawn      = %.2f%%", draws * 100.0 / totalGames));
            for (int j = 1; j <= Heuristic.num_heuristics; j++) {
                double pct = (heuristic_wins[j] * 100.0 / totalGames);
                System.out.println(String.format("Heuristic %d wins = %.2f%%", j, pct));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public static void run_AI_vs_AI_same_heuristics(){
        Player p0 = new Player(0, "AI-1", false, true);
        Player p1 = new Player(1, "AI-2", false, false);

        int iterations = 100;

        for(int j=1; j<= Heuristic.num_heuristics; j++){
            int firstPlayerWins = 0;
            for(int i=0; i<iterations; i++){
                p0.heuristic_id = j;
                p1.heuristic_id = j;
                Game game = new Game(p0, p1);
                int winningPlayerId = game.run(false);
                if(winningPlayerId == 0){
                    firstPlayerWins++;
                }
            }
            System.out.println(String.format("Heuristic %d -> First moving player wins = %.2f%%", j, (firstPlayerWins*100.0/iterations)));
        }
    }
}
