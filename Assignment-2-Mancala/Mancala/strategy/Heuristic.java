package strategy;

import mancala.State;

public class Heuristic{
    public static int num_heuristics = 4;
    public static int get_heuristic(State state, int heuristic_id) {
        int W1, W2, W3, W4;
        int heuristic;
        switch (heuristic_id){
            case 1:
                heuristic = state.getStorageDifference();
                break;
            case 2:
                W1 = 1; W2 = 2;
                heuristic = W1*state.getStorageDifference() + W2*state.getSideStonesDifference();
                break;
            case 3:
                W1 = 2; W2 = 1; W3 = 1 ;
                heuristic = W1*state.getStorageDifference() + W2*state.getSideStonesDifference();
                if(state.currentPlayerId == 0){
                    heuristic += W3*state.additionalMoves[state.currentPlayerId];
                } else {
                    heuristic -= W3*state.additionalMoves[state.currentPlayerId];
                }
                break;
            case 4:
                W1 = 1; W2 = 1; W3 = 2; W4 = 2;
                heuristic = W1*state.getStorageDifference() + W2*state.getSideStonesDifference() + W3*state.additionalMoves[state.currentPlayerId] + W4*state.capturedStones[state.currentPlayerId];
                if(state.currentPlayerId == 0){
                    heuristic += (W3*state.additionalMoves[state.currentPlayerId] + W4*state.capturedStones[state.currentPlayerId]);
                } else {
                    heuristic -= (W3*state.additionalMoves[state.currentPlayerId] + W4*state.capturedStones[state.currentPlayerId]);
                }
                break;
            default:
                System.out.println("Invalid heuristic id");
                heuristic = -1;
        }
        return heuristic;
    }
}
