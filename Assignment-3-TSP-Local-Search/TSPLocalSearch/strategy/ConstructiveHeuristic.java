package strategy;

import problem.TSP;
import problem.TSPSolution;

public interface ConstructiveHeuristic {
    public TSPSolution constructSolution(TSP t);
}
