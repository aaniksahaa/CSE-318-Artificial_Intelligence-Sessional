package mancala;

import java.util.Arrays;

import static util.Config.*;

public class State {
    public int[] board;

    // here, assuming that the player at the side with pits 0..5 is Maximizer
    //  and, the player at the side with pits 7..12 is Minimizer
    public int currentPlayer;   // 0 or 1

    // for two players
    public int[] additionalMoves;
    public int[] capturedStones;

    // initial state
    public State(int currentPlayer) {
        this.board = new int[total];
        for(int i=0; i<total; i++){
            if(i==mancalaIndices[0] || i==mancalaIndices[1]){
                board[i] = 0;
            } else {
                board[i] = INITIAL_STONES_ON_EACH_PIT;
            }
        }
        this.currentPlayer = currentPlayer;
        this.additionalMoves = new int[]{0,0};
        this.capturedStones = new int[]{0,0};
    }

    // creating deepcopy
    public State(State other) {
        this.board = Arrays.copyOf(other.board, other.board.length);
        this.currentPlayer = other.currentPlayer;
        this.additionalMoves = Arrays.copyOf(other.additionalMoves, other.additionalMoves.length);
        this.capturedStones = Arrays.copyOf(other.capturedStones, other.capturedStones.length);
    }

    public int[] getRangeForPlayer() {
        int firstPitIndex = (currentPlayer == 0) ? 0 : (PITS_ON_EACH_SIDE + 1);
        int lastPitIndex = firstPitIndex + PITS_ON_EACH_SIDE - 1;

        return new int[] {firstPitIndex, lastPitIndex};
    }

    public Boolean isOwnPit(int pitIndex){
        int firstPitIndex, lastPitIndex;
        firstPitIndex = (currentPlayer==0) ? 0 : (PITS_ON_EACH_SIDE+1);
        lastPitIndex = firstPitIndex + PITS_ON_EACH_SIDE - 1;
        if(!(pitIndex >= firstPitIndex && pitIndex <= lastPitIndex)){
            return false;
        }
        return true;
    }

    public Boolean canMove(int pitIndex){
        if(!isOwnPit(pitIndex)){
            return false;
        }
        if(board[pitIndex]<=0){
            return false;
        }
        return true;
    }

    public State getNextState(int movedPitIndex){
        if(!canMove(movedPitIndex)){
            return null;    // move not possible
        }
        State nextState = new State(this);
        nextState.board[movedPitIndex] = 0;
        int remainingStones = board[movedPitIndex];
        int startFrom = getNextPitIndex(movedPitIndex);
        int i;
        for(i=startFrom; remainingStones>0; i=getNextPitIndex(i)){
            if(i==mancalaIndices[1 - currentPlayer]){
                continue;   // skip opponent's mancala
            }
            nextState.board[i]++;
            remainingStones--;
        }
        int lastDroppedPit = getPrevPitIndex(i);
        if(lastDroppedPit == mancalaIndices[currentPlayer]){
            nextState.additionalMoves[currentPlayer]++;
            nextState.currentPlayer = this.currentPlayer;
        } else {
            if(isOwnPit(lastDroppedPit) && (nextState.board[lastDroppedPit] == 1)){
                // capture
                
            }
        }
    }

    // in counterClockWise order
    private int getNextPitIndex(int pitIndex){
        return (pitIndex+1)%total;
    }

    // in counterClockWise order
    private int getPrevPitIndex(int pitIndex){
        return (pitIndex-1+total)%total;
    }
}
