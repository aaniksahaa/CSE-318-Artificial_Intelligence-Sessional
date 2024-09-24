package mancala;

import player.Player;
import strategy.Minimax;
import static util.Config.*;

public class Game {
    public Player[] players = new Player[2];
    public State currentState;

    public Game(Player p0, Player p1) {
        if(!(p0.id==0 && p1.id==1)){
            System.out.println("Error! Players expected to have ids 0 and 1 respectively");
        }
        players[0] = p0;
        players[1] = p1;
        int firstPlayer = (p0.takesFirstMove ? 0 : 1);
        currentState = new State(firstPlayer);
        System.out.println("Initial Board:");
        System.out.println(currentState);
    }

    // one single step
    public void step(){
        Player currentPlayer = players[currentState.currentPlayerId];
        System.out.println("It is " + currentPlayer.name + "' turn.");
        if(currentPlayer.isHuman){

        } else {
            Minimax minimax = new Minimax(5, currentPlayer.heuristic_id);
            int[]arr = minimax.run(currentState, -INFINITY, INFINITY, 0);
            int bestMoveIndex = arr[1];
            System.out.println(currentPlayer.name + " moved from pit "+bestMoveIndex);
            currentState = currentState.getNextState(bestMoveIndex);
            System.out.println("Current board:");
            System.out.println(currentState);
        }
    }

    public void run(){
        while (!currentState.isLeaf()){
            step();
        }
    }
}
