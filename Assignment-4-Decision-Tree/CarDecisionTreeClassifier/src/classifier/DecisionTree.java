package classifier;

import util.Attribute;
import util.Config.*;
import util.DataPoint;
import util.Dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DecisionTree {
    AttributeSelectionStrategy attributeSelectionStrategy;
    EvaluationMetric evaluationMetric;

    public Node root;

    public DecisionTree(Dataset dataset, AttributeSelectionStrategy attributeSelectionStrategy, EvaluationMetric evaluationMetric) throws Exception {
        if(dataset.datapoints.isEmpty()){
            throw new Exception("Sorry! Empty dataset cannot be processed.");
        }
        this.attributeSelectionStrategy = attributeSelectionStrategy;
        this.evaluationMetric = evaluationMetric;
        this.root = constructDecisionTree(dataset.datapoints, dataset.attributes, new ArrayList<>());
        System.out.println("Done!");
    }

    public Node constructDecisionTree(ArrayList<DataPoint> datapoints, ArrayList<Attribute>remainingAttributes, ArrayList<DataPoint> parentDatapoints) throws Exception {
        if(datapoints.isEmpty()){
            return new Node(true, getPluralityLabel(parentDatapoints), null);
        } else if (isOfSameClass(datapoints)) {
            return new Node(true, datapoints.get(0).label, null);
        } else if (remainingAttributes.isEmpty()) {
            return new Node(true, getPluralityLabel(datapoints), null);
        } else {
            double metricBeforeSplit = getMetric(datapoints);
            Attribute optimalAttribute = remainingAttributes.get(0);
            double maxGain = Double.NEGATIVE_INFINITY;
            for(Attribute a: remainingAttributes){
                double metricAfterSplit = getMetricAfterSplit(datapoints, a);
                double gain = metricBeforeSplit - metricAfterSplit;
                if(gain > maxGain){
                    optimalAttribute = a;
                    gain = maxGain;
                }
            }
            ArrayList<Attribute> currentRemainingAttributes = new ArrayList<>(remainingAttributes);
            currentRemainingAttributes.remove(optimalAttribute);
            HashMap<String, ArrayList<DataPoint>> splitMap = getSplitMap(datapoints, optimalAttribute);
            Node node = new Node(false, null, optimalAttribute);
            for(String value: optimalAttribute.possibleValues){
                ArrayList<DataPoint> childDataPoints = splitMap.getOrDefault(value, new ArrayList<>());
                node.addChild(value, constructDecisionTree(childDataPoints, currentRemainingAttributes, datapoints));
            }
            return node;
        }
    }
    
    public Boolean isOfSameClass(ArrayList<DataPoint> datapoints){
        if(datapoints.isEmpty()) return true;
        String firstLabel = datapoints.get(0).label;
        for(DataPoint d: datapoints){
            if(!d.label.equals(firstLabel)){
                return false;
            }
        }
        return true;
    }

    public String getPluralityLabel(ArrayList<DataPoint> datapoints){
        HashMap<String, Integer> labelCounts = getLabelCountMap(datapoints);

        int maxCount = 0;
        ArrayList<String> maxLabels = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : labelCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxLabels.clear();
                maxLabels.add(entry.getKey());
            } else if (entry.getValue() == maxCount) {
                maxLabels.add(entry.getKey());
            }
        }

        // in case of tie
        // break tie randomly
        return maxLabels.get(new Random().nextInt(maxLabels.size()));
    }

    public double getMetricAfterSplit(ArrayList<DataPoint> datapoints, Attribute a) throws Exception {
        double metric = 0.0;

        int total = datapoints.size();

        HashMap<String, ArrayList<DataPoint>> splitMap = getSplitMap(datapoints, a);

        for (Map.Entry<String, ArrayList<DataPoint>> entry : splitMap.entrySet()) {
            ArrayList<DataPoint> currentSplitDatapoints = entry.getValue();
            double weight = currentSplitDatapoints.size()*1.0 / total;
            metric += weight* getMetric(entry.getValue());
        }

        return metric;
    }

    public double getMetric(ArrayList<DataPoint> datapoints) throws Exception {
        double metric;

        int total = datapoints.size();

        HashMap<String, Integer> labelCounts = getLabelCountMap(datapoints);

        if(evaluationMetric == EvaluationMetric.INFORMATION_GAIN){
            double entropy = 0.0;
            for (Map.Entry<String, Integer> entry : labelCounts.entrySet()) {
                double p = entry.getValue()*1.0 / total;
                if(p != 0){
                    entropy += (-(p*log2(p)));
                }
            }
            metric = entropy;
        } else if (evaluationMetric == EvaluationMetric.GINI_IMPURITY){
            double sum = 1.0;
            for (Map.Entry<String, Integer> entry : labelCounts.entrySet()) {
                double p = entry.getValue()*1.0 / total;
                if(p != 0){
                    sum += p*p;
                }
            }
            metric = 1-sum;
        } else{
            throw new Exception("Unknown Metric");
        }

        return metric;
    }

    private HashMap<String, Integer> getLabelCountMap(ArrayList<DataPoint> datapoints){
        HashMap<String, Integer> labelCounts = new HashMap<>();
        for (DataPoint d : datapoints) {
            labelCounts.put(d.label, labelCounts.getOrDefault(d.label, 0) + 1);
        }
        return labelCounts;
    }

    private HashMap<String, ArrayList<DataPoint>> getSplitMap(ArrayList<DataPoint> datapoints, Attribute a){
        HashMap<String, ArrayList<DataPoint>> splitMap = new HashMap<>();
        for (DataPoint d : datapoints) {
            String value = d.attributeValues.get(a);
            if(!splitMap.containsKey(value)){
                splitMap.put(value, new ArrayList<>());
            }
            splitMap.get(value).add(d);
        }
        return splitMap;
    }

    private double log2(double v){
        return (Math.log(v)/(Math.log(2)));
    }
}
