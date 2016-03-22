package graph;

import java.util.ArrayList;

public class Node {
  
  private final String name;
  private ArrayList<Node> neighbours;
  private Node parent;
  private int distance;
  
  public Node(String name, ArrayList<Node> neighbours) {
    this.name = name;
    this.neighbours = neighbours;
    parent = null;
    distance = -1;
  }
  
  public Node(String name) {
    this.name = name;
    neighbours = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public ArrayList<Node> getNeighbours() {
    return neighbours;
  }
  
  public void addNeighbour(Node neighbour) {
    if (!neighbours.contains(neighbour)) {
      neighbours.add(neighbour);
    } else {
      System.err.println("This node already had " + neighbour + " as a neighbour.");
    }
  }
  
  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  @Override
  public String toString() {
    String output = "(" + name + " : [";
    for (Node n : neighbours) {
      output += n.getName() + ",";
    }
    // Removes the trailing comma ($ signifies the end of the string)
    output = output.replaceAll(",$", "");
    output += "])";
    return output;
  }
}
