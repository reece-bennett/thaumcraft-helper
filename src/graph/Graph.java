package graph;

import java.util.HashMap;

public class Graph {

  private HashMap<String, Node> nodes;
  
  public Graph() {
    nodes = new HashMap<>();
  }

  public HashMap<String, Node> getAll() {
    return nodes;
  }
  
  public void add(Node node) {
    if (nodes.containsKey(node.getName())) {
      System.err.println("There is already an entry with this name, overwriting.");
    }
    nodes.put(node.getName(), node);
  }
  
  public Node get(String name) {
    return nodes.get(name);
  }
  
  public void connect(String name1, String name2) {
    Node node1 = nodes.get(name1);
    Node node2 = nodes.get(name2);
    
    if (node1 == null) {
      System.err.println(name1 + " was not found in the graph.");
      return;
    } else if (node2 == null) {
      System.err.println(name2 + " was not found in the graph.");
      return;
    }
    
    node1.addNeighbour(node2);
    node2.addNeighbour(node1);
  }
  
  public int size() {
    return nodes.size();
  }
  
  @Override
  public String toString() {
    return nodes.values().toString();
  }
}
