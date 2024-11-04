package problem;

import java.util.ArrayList;

public class TSPSolution {
    public TSP tsp;
    public ArrayList<Integer>cycle;  // list of vertices in the cycle
    public double cost;

    public TSPSolution(TSP tsp, ArrayList<Integer> cycle) {
        this.tsp = tsp;
        this.cycle = cycle;
        this.cost = 0;
        int L = cycle.size();
        for(int i=0; i<L; i++){
            int u = cycle.get(i);
            int v = cycle.get((i+1)%L);
            this.cost += tsp.getDistance(u,v);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("Generated Solution to "+tsp.name+":\n\n");
        sb.append("Number of nodes = "+tsp.n+"\n");
        sb.append("Nodes = ");
        for(int u: cycle){
            sb.append(u + " - ");
        }
        sb.append("\b\b\b\n");
        sb.append(String.format("Total cost = %.4f", cost));
        sb.append("\n");
        return sb.toString();
    }
}
