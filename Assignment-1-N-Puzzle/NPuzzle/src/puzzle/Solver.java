package puzzle;

import heuristics.Heuristic;

import java.util.*;

public class Solver {
    public Heuristic heuristic;

    public Solver(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public void solve(Puzzle puzzle){
        System.out.println(heuristic.name + " Heuristic: ");
        System.out.println("---------------------------------\n");

        Boolean useClosedMap = true;
        Map<State,Boolean> closedMap = new HashMap<>();

        PriorityQueue<State> pq = new PriorityQueue<>(
                Comparator.comparingInt(s -> s.getPriority(heuristic, puzzle.targetBoard))
        );

        State initialState = new State(puzzle.inputBoard, 0, null);
        pq.add(initialState);

        if(useClosedMap) {
            closedMap.put(initialState, true);
        }

        int explored = 1;
        int expanded = 0;

        State finalState = null;
        while (!pq.isEmpty()){
            State s = pq.poll();
            expanded++;
            if(s.board.equals(puzzle.targetBoard)){
                finalState = s;
                break;
            }
            ArrayList<State>neighborStates = s.getNeighborStates();
            for(State neighborState: neighborStates){
                if(useClosedMap){
                    if(!closedMap.containsKey(neighborState)){
                        closedMap.put(neighborState,true);
                        pq.add(neighborState);
                        explored++;
                    }
                }
                else {
                    pq.add(neighborState);
                    explored++;
                }
            }
        }

        if(finalState != null){
            ArrayList<State>ancestorStates = finalState.getAncestorStates();
            System.out.println("Number of Explored Nodes = " + explored);
            System.out.println("Number of Expanded Nodes = " + expanded);
            System.out.println("Number of steps needed = " + ancestorStates.size());
            System.out.println("");
            ArrayList<State>solutionPath = ancestorStates;
            Collections.reverse(solutionPath);
            solutionPath.add(finalState);
            for(State s: solutionPath){
                System.out.println(s.board+ "\n");
            }
        }
    }
}
