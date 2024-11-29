package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dataset {
    public String name;
    public ArrayList<Attribute> attributes;
    public ArrayList<String> possibleLabels;
    public ArrayList<DataPoint> datapoints;

    public Dataset(String name, ArrayList<Attribute> attributes, ArrayList<String> possibleLabels, String inputPath) {
        this.name = name;
        this.attributes = attributes;
        this.possibleLabels = possibleLabels;
        this.datapoints = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] values = line.split(",");
                if (values.length != attributes.size() + 1) { // +1 for the label
                    throw new IllegalArgumentException(
                            String.format("Invalid number of values at line %d. Expected %d, got %d",
                                    lineNumber, attributes.size() + 1, values.length)
                    );
                }

                HashMap<Attribute, String> attributeValues = new HashMap<>();

                for (int i = 0; i < attributes.size(); i++) {
                    String value = values[i].trim();
                    Attribute currentAttribute = attributes.get(i);

                    boolean isValidValue = false;
                    for (String possibleValue : currentAttribute.possibleValues) {
                        // flexible matching
                        if (value.replace("-", "").equalsIgnoreCase(possibleValue.replace("-", ""))) {
                            value = possibleValue;
                            isValidValue = true;
                            break;
                        }
                    }

                    if (!isValidValue) {
                        throw new IllegalArgumentException(
                                String.format("Invalid value '%s' for attribute '%s' at line %d",
                                        value, currentAttribute.name, lineNumber)
                        );
                    }

                    attributeValues.put(currentAttribute, value);
                }

                String label = values[values.length - 1].trim().replace("-","");
                if (!possibleLabels.contains(label)) {
                    throw new IllegalArgumentException(
                            String.format("Invalid label '%s' at line %d", label, lineNumber)
                    );
                }

                datapoints.add(new DataPoint(attributeValues, label));
            }

            if (datapoints.isEmpty()) {
                throw new IllegalArgumentException("No valid data points found in the input file");
            }

            System.out.println("Parsing Successful!");

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + inputPath, e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\nDataset: %s\n", name));
        sb.append(String.format("Number of datapoints: %d\n", datapoints.size()));
        sb.append("Attributes:\n");
        for (Attribute attr : attributes) {
            sb.append(String.format("  - %s\n", attr.toString()));
        }
        sb.append(String.format("Possible labels (%d): %s\n",
                possibleLabels.size(),
                String.join(", ", possibleLabels)));
        return sb.toString();
    }
}
