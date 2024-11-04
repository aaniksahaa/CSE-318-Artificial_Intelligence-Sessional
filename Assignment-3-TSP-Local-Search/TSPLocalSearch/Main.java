import heuristics.*;
import problem.TSP;
import problem.TSPSolution;
import util.Edge;
import util.FileParser;
import util.GraphUtil;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        TSP tsp = FileParser.parseTSPFile("./benchmarks/a280.tsp");
        System.out.println(tsp.n);

        ConstructiveHeuristic con;

        // con = new NearestNeighbourHeuristic();
        // con = new CheapestInsertionHeuristic();
        // con = new RandomInsertionHeuristic();
        con = new MSTSimpleHeuristic();

        PerturbativeHeuristic per = new TwoOptHeuristic();

        TSPSolution sol = con.constructSolution(tsp, 3, false);
        System.out.println(sol);

        sol = per.perturbSolution(sol, false);

        System.out.println(sol);
        System.out.println(sol.cycle.size());

    }
}
