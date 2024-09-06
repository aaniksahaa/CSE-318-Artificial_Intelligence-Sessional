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
        System.out.println(heuristic.name + " Heuristic: ");
        System.out.println("---------------------------------\n");

        PriorityQueue<State> pq = new PriorityQueue<>(
                Comparator.comparingInt(s -> s.getPriority(heuristic, puzzle.targetBoard))
        );
        State initialState = new State(puzzle.inputBoard, 0, null);
        pq.add(initialState);
        State finalState = null;
        while (!pq.isEmpty()){
            State s = pq.poll();
            if(s.board.equals(puzzle.targetBoard)){
                finalState = s;
                break;
            }
            ArrayList<State>neighborStates = s.getNeighborStates();
            for(State neighborState: neighborStates){
                pq.add(neighborState);
            }
        }

        if(finalState != null){
            ArrayList<State>ancestorStates = finalState.getAncestorStates();
            System.out.println("Number of steps needed = " + ancestorStates.size());
        }
    }
}
