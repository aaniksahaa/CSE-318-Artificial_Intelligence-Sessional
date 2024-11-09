import heuristics.*;
import problem.TSP;
import problem.TSPSolution;
import util.FileParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TSP tsp = FileParser.parseTSPFile("./benchmarks/a280.tsp");
        System.out.println(tsp.n);

        ConstructiveHeuristic con;

        // con = new NearestNeighbour();
        // con = new CheapestInsertion();
        // con = new RandomInsertion();
        con = new MSTSimple();

        PerturbativeHeuristic per;
        per = new TwoOpt();
//        per = new NodeSwap();

        TSPSolution sol = con.constructSolution(tsp, 3, false);
        System.out.println(sol);

        sol = per.perturbSolution(sol, true);

        System.out.println(sol);
        System.out.println(sol.cycle.size());

    }
}
