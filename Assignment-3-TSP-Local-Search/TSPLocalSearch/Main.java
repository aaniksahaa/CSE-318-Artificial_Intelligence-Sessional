import problem.TSP;
import util.FileParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TSP t = FileParser.parseTSPFile("./benchmarks/a280.tsp");
        System.out.println(t.n);
    }
}
