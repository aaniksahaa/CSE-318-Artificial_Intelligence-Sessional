import util.Edge;
import util.GraphUtil;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(1,5,1));
        edges.add(new Edge(1,3,1));
        edges.add(new Edge(3,4,1));
        edges.add(new Edge(3,2,1));
        edges.add(new Edge(1,6,1));

        ArrayList<Integer>tour = GraphUtil.findEulerTour(6, edges);

        for(int u: tour){
            System.out.println(u);
        }
    }
}
