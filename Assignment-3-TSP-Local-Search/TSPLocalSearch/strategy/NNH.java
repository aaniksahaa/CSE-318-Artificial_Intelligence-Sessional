package strategy;

import problem.TSP;
import problem.TSPSolution;

import java.util.Random;

public class NNH implements ConstructiveHeuristic{

    @Override
    public TSPSolution constructSolution(TSP tsp) {
        TSPSolution solution;
        Random random = new Random();
        int u = 1 + random.nextInt(tsp.n);
        return null;
    }
}
