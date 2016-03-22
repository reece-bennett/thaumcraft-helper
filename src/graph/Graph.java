package graph;

import java.util.ArrayList;

public class Graph {

  private ArrayList<Node> nodes;
  
  public Graph() {
    nodes = new ArrayList<>();
  }

  public ArrayList<Node> getNodes() {
    return nodes;
  }
  
  public void addNode(Node node) {
    nodes.add(node);
  }
  
  public int size() {
    return nodes.size();
  }
}
