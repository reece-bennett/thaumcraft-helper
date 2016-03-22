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
  
  public int size() {
    return nodes.size();
  }
}
