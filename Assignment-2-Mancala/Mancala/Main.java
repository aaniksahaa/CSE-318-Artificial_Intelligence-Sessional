import mancala.Game;
import mancala.State;
import player.Player;
import strategy.Heuristic;

public class Main {
    public static void main(String[] args) {
        run_AI_vs_AI_diff_heuristics();
        run_AI_vs_AI_same_heuristics();
    }

    public static void run_AI_vs_AI_diff_heuristics(){
        Player p0 = new Player(0, "AI-1", 1, false, true);
        Player p1 = new Player(1, "AI-2", 2, false, false);

        int totalGames = 0;
        int draws = 0;
        int[] heuristic_wins = new int[Heuristic.num_heuristics+1];

        int iterations = 100;
        for(int i=0; i<iterations; i++){
            for(int j=1; j<= Heuristic.num_heuristics; j++){
                for(int k=1; k<= Heuristic.num_heuristics; k++){
                    if(j!=k){
                        p0.heuristic_id = j;
                        p1.heuristic_id = k;
                        Game game = new Game(p0, p1);
                        int winningPlayerId = game.run(false);
                        totalGames++;
                        if(winningPlayerId == -1){
                            draws++;
                        } else {
                            heuristic_wins[game.players[winningPlayerId].heuristic_id]++;
                        }
                    }
                }
            }
        }

        System.out.println("\nTotal Games = "+totalGames+"\n");
        System.out.println(String.format("Games drawn      = %.2f%%",draws*100.0/totalGames));
        for(int j=1; j<= Heuristic.num_heuristics; j++){
            double pct = (heuristic_wins[j]*100.0/totalGames);
            System.out.println(String.format("Heuristic %d wins = %.2f%%", j, pct));
        }
    }

    public static void run_AI_vs_AI_same_heuristics(){
        Player p0 = new Player(0, "AI-1", 1, false, true);
        Player p1 = new Player(1, "AI-2", 1, false, false);

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
