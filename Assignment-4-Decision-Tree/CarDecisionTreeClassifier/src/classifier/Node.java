package classifier;

import util.Attribute;

import java.util.HashMap;

public class Node {
    public Boolean isLeaf;
    // only used in case of Leaf
    public String outputLabel;
    public Attribute attributeToTest;
    // mapping from attribute value to Node
    // this will be null in case of non-leaf node
    public HashMap<String, Node> childrenMap = new HashMap<>();

    public Node(Boolean isLeaf, String outputLabel, Attribute attributeToTest) {
        this.isLeaf = isLeaf;
        this.outputLabel = outputLabel;
        this.attributeToTest = attributeToTest;
    }

    public void addChild(String value, Node child){
        childrenMap.put(value, child);
    }
}
