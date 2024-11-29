import classifier.DecisionTree;
import util.Attribute;
import util.Config.*;
import util.Dataset;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Attribute> attributes = new ArrayList<>();

        // input attribute values and output labels
        // should not have hyphens

        attributes.add(new Attribute("buying", new ArrayList<>(Arrays.asList("vhigh", "high", "med", "low"))));
        attributes.add(new Attribute("maint", new ArrayList<>(Arrays.asList("vhigh", "high", "med", "low"))));
        attributes.add(new Attribute("doors", new ArrayList<>(Arrays.asList("2", "3", "4", "5more"))));
        attributes.add(new Attribute("persons", new ArrayList<>(Arrays.asList("2", "4", "more"))));
        attributes.add(new Attribute("lug_boot", new ArrayList<>(Arrays.asList("small", "med", "big"))));
        attributes.add(new Attribute("safety", new ArrayList<>(Arrays.asList("low", "med", "high"))));

        Dataset carDataset = new Dataset("car-dataset", attributes, new ArrayList<>(Arrays.asList("unacc", "acc", "good", "vgood")), "./car-evaluation-dataset/car.data");

        System.out.println(carDataset);

        Dataset[] splitDatasets = carDataset.trainTestSplit(10);

        Dataset trainDataset = splitDatasets[0];
        Dataset testDataset = splitDatasets[1];

        System.out.println(trainDataset.datapoints.size());

        DecisionTree decisionTree = new DecisionTree(trainDataset, AttributeSelectionStrategy.BEST, EvaluationMetric.INFORMATION_GAIN);

        System.out.println(decisionTree.calculateAccuracy(testDataset));
    }
}