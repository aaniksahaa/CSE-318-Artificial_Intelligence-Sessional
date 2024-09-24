package player;

import strategy.Heuristic;

public class Player {
    // player_id can be 0 or 1
    // 0 -> on side with 0...5
    // 1 -> on side with 7...12
    public int id;
    public String name;
    public int heuristic_id;
    public Boolean isHuman;
    public Boolean takesFirstMove;

    public Player(int id, String name, int heuristic_id, Boolean isHuman, Boolean takesFirstMove) {
        this.id = id;
        this.name = name;
        this.heuristic_id = heuristic_id;
        this.isHuman = isHuman;
        this.takesFirstMove = takesFirstMove;
    }
}
