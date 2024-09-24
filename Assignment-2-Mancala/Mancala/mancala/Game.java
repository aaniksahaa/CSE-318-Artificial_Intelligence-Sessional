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
    }

    // one single step
    public void step(Boolean verbose){
        Player currentPlayer = players[currentState.currentPlayerId];

        if(verbose) {
            System.out.println("It is " + currentPlayer.name + "' turn.");
        }

        if(currentPlayer.isHuman){

        } else {
            Minimax minimax = new Minimax(6, currentPlayer.heuristic_id);
            int[]arr = minimax.run(currentState, -INFINITY, INFINITY, 0);
            int bestMoveIndex = arr[1];
            currentState = currentState.getNextState(bestMoveIndex);

            if(verbose){
                System.out.println(currentPlayer.name + " moved from pit "+bestMoveIndex);
                System.out.println("Current board:");
                System.out.println(currentState);
            }
        }
    }

    public int run(Boolean verbose){
        if(verbose){
            System.out.println("Initial Board:");
            System.out.println(currentState);
        }

        while (!currentState.isLeaf()){
            step(verbose);
        }
        int[] finalTotalStones = currentState.getFinalTotalStones();

        if(verbose){
            System.out.println("\n");
            System.out.println(players[0].name + " -> " + finalTotalStones[0] + " stones, " + players[1].name + " -> " + finalTotalStones[1] + " stones");
        }

        int winningPlayerId;
        if(finalTotalStones[0] == finalTotalStones[1]){
            winningPlayerId = -1;
        } else{
            if(finalTotalStones[0] > finalTotalStones[1]){
                winningPlayerId = 0;
            } else {
                winningPlayerId = 1;
            }
        }

        if(verbose){
            if(winningPlayerId == -1){
                System.out.println("Draw !!!");
            } else {
                System.out.println(players[winningPlayerId].name + " won the game!");
            }
            System.out.println("\n");
        }
        return winningPlayerId;
    }
}
