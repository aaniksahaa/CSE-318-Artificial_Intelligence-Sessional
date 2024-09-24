import mancala.Game;
import mancala.State;
import player.Player;
import strategy.Heuristic;

public class Main {
    public static void main(String[] args) {
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
}
