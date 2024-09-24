import mancala.Game;
import player.Player;

public class Main {
    public static void main(String[] args) {
        Player p0 = new Player(0, "AI-1", 1, false, true);
        Player p1 = new Player(1, "AI-2", 2, false, false);
        Game game = new Game(p0, p1);
        game.run();
    }
}
