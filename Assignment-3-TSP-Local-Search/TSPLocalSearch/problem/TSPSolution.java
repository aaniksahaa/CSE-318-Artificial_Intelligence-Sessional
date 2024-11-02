package problem;

import java.util.ArrayList;

public class TSPSolution {
    public TSP problem;
    public ArrayList<Integer>cycle;  // list of vertices in the cycle
    public double cost;

    public TSPSolution(TSP problem, ArrayList<Integer> cycle, double cost) {
        this.problem = problem;
        this.cycle = cycle;
        this.cost = cost;
    }
}
