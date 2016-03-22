package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
  
  public void connect(String name, String[] others) {
    Node node1 = nodes.get(name);

    for (int i = 0; i < others.length; i++) {
      Node node2 = nodes.get(others[i]);
      
      if (node1 == null) {
        System.err.println(name + " was not found in the graph.");
        return;
      } else if (node2 == null) {
        System.err.println(others[i] + " was not found in the graph.");
        return;
      }
      
      node1.addNeighbour(node2);
      node2.addNeighbour(node1);
    }
  }
  
  public void connect(String name, String other) {
    connect(name, new String[] {other});
  }
  
  public ArrayList<Node> getPath(String start, String end) {
    LinkedList<Node> frontier = new LinkedList<>();
    LinkedList<Node> explored = new LinkedList<>();
    frontier.add(nodes.get(start));
    
    // Used for backtracking the graph
    HashMap<Node, Node> connections = new HashMap<>();
    
    while (!frontier.isEmpty()) {
      Node current = frontier.removeFirst();
      
      if (current.getName().equals(end)) {
        Node backtrack = nodes.get(end);
        ArrayList<Node> path = new ArrayList<>();
        while (!backtrack.getName().equals(start)) {
          path.add(0, backtrack);
          backtrack = connections.get(backtrack);
        }
        path.add(0, backtrack);
        return path;
        
      } else if (!explored.contains(current)) {
        explored.add(current);
        ArrayList<Node> neighbours = current.getNeighbours();
        for (Node n : neighbours) {
          if (!connections.containsKey(n)) {
            connections.put(n, current);
          }
          if (!frontier.contains(n) && !explored.contains(n)) {
            frontier.add(n);
          }
        }
      }
    }
    return null;
  }
  
  public int size() {
    return nodes.size();
  }
  
  @Override
  public String toString() {
    return nodes.values().toString();
  }
}
