import heuristics.CheapestInsertionHeuristic;
import heuristics.RandomInsertionHeuristic;
import problem.TSP;
import problem.TSPSolution;
import heuristics.ConstructiveHeuristic;
import heuristics.NearestNeighbourHeuristic;
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
        con = new RandomInsertionHeuristic();

        TSPSolution sol = con.constructSolution(tsp, 3, true);

        System.out.println(sol);

    }
}
