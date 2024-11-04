import heuristics.CheapestInsertionHeuristic;
import problem.TSP;
import problem.TSPSolution;
import heuristics.ConstructiveHeuristic;
import heuristics.NearestNeighbourHeuristic;
import util.FileParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TSP tsp = FileParser.parseTSPFile("./benchmarks/a280.tsp");
        System.out.println(tsp.n);

        ConstructiveHeuristic con;

        // con = new NearestNeighbourHeuristic();
        con = new CheapestInsertionHeuristic();

        TSPSolution sol = con.constructSolution(tsp, 3, true);

        System.out.println(sol);

    }
}
