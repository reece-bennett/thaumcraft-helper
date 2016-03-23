package graph;

import java.util.ArrayList;
import java.util.Arrays;
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
    connect(name, new String[] { other });
  }

  /*public ArrayList<Node> getPath(String start, String end) {
    // Start by resetting the parents of all nodes in the graph
    for (Node n : nodes.values()) {
      n.setParent(null);
    }

    LinkedList<Node> frontier = new LinkedList<>();
    LinkedList<Node> explored = new LinkedList<>();

    // Start the search by adding the start node to the frontier
    frontier.add(nodes.get(start));

    while (!frontier.isEmpty()) {
      Node current = frontier.removeFirst();

      if (current.getName().equals(end)) {
        // If the current node is the end point the search is complete, return the path
        ArrayList<Node> path = new ArrayList<>();
        Node parent = current;
        while (!parent.getName().equals(start)) {
          path.add(0, parent);
          parent = parent.getParent();
        }
        path.add(0, parent);
        return path;

      } else if (!explored.contains(current)) {
        // Else expand all children of the current node and if not already in frontier or explored,
        // add them to the frontier
        explored.add(current);
        for (Node n : current.getNeighbours()) {
          if (!frontier.contains(n) && !explored.contains(n)) {
            n.setParent(current);
            frontier.add(n);
          }
        }
      }
    }
    return null;
  }*/

  public LinkedList<Node> getPath(String start, String end, int steps) {
    LinkedList<LinkedList<Node>> frontier = new LinkedList<>();
    frontier.add(new LinkedList<>(Arrays.asList(nodes.get(start))));
    
    while (!frontier.isEmpty()) {
      LinkedList<Node> path = frontier.removeFirst();
      if (path.getLast().getName().equals(end) && path.size() > steps+1) {
        return path;
      } else {
        ArrayList<Node> neighbours = path.getLast().getNeighbours();
        for (Node n : neighbours) {
          if (!path.contains(n)) {
            @SuppressWarnings("unchecked")
            LinkedList<Node> newPath = (LinkedList<Node>) path.clone();
            newPath.add(n);
            frontier.add(newPath);
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
