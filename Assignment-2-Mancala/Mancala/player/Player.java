package player;

import strategy.Heuristic;

public class Player {
    public String name;
    public Heuristic heuristic;
    public Boolean isHuman;

    public Player(String name, Heuristic heuristic, Boolean isHuman) {
        this.name = name;
        this.heuristic = heuristic;
        this.isHuman = isHuman;
    }
}
