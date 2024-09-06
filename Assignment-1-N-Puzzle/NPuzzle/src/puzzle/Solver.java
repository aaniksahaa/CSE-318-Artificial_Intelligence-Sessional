package puzzle;

import heuristics.Heuristic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solver {
    public Heuristic heuristic;

    public Solver(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public void solve(Puzzle puzzle){
        PriorityQueue<State> pq = new PriorityQueue<>(
                Comparator.comparingInt(s -> s.getPriority(heuristic, puzzle.targetBoard))
        );
        State initialState = new State(puzzle.inputBoard, 0, null);
        pq.add(initialState);
        while (!pq.isEmpty()){
            State s = pq.poll();
            if(s.board.equals(puzzle.targetBoard)){
                System.out.println("Solution Found");
                break;
            }
            ArrayList<State>neighborStates = s.getNeighborStates();
            for(State neighborState: neighborStates){
                pq.add(neighborState);
            }
        }
    }
}
