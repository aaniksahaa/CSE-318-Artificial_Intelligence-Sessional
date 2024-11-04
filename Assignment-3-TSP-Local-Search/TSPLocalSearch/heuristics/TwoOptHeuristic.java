package heuristics;

import problem.TSP;
import problem.TSPSolution;

import java.util.ArrayList;
import java.util.Collections;

public class TwoOptHeuristic implements PerturbativeHeuristic{

    @Override
    public TSPSolution perturbSolution(TSPSolution solution, boolean verbose) {
        TSP tsp = solution.tsp;
        ArrayList<Integer>cycle = new ArrayList<>();

        // keeping the original solution intact
        cycle.addAll(solution.cycle);

        int L = cycle.size();
        // we will need to reverse the segment [i+1:j] later
        // so we set the loop traversal as such
        for(int i=0; i<L-1; i++){
            for(int j=i+2; j<L; j++){
                int a,b,c,d;

                a = cycle.get(i);
                b = cycle.get((i+1)%L);
                c = cycle.get(j);
                d = cycle.get((j+1)%L);

                if(a!=c && a!=d && b!=c && b!=d){
                    double gain = tsp.getDistance(a,c) + tsp.getDistance(b,d) - tsp.getDistance(a,b) - tsp.getDistance(c,d);
                    // if swapping results in better solution
                    if(gain < 0){
                        int k1 = i+1;
                        int k2 = j;
                        while(k1<k2){
                            Collections.swap(cycle,k1,k2);
                            k1++;
                            k2--;
                        }
                        System.out.println("Improved by "+(-gain));
                    }
                }
            }
        }

        TSPSolution perturbedSolution = new TSPSolution(tsp, cycle);

        return perturbedSolution;
    }
}
