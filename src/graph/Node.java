package graph;

import java.util.ArrayList;

public class Node {
  
  private final String name;
  private ArrayList<Node> neighbours;
  
  public Node(String name, ArrayList<Node> neighbours) {
    this.name = name;
    this.neighbours = neighbours;
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
  
  @Override
  public String toString() {
    String output = "(" + name + " : [";
    for (Node n : neighbours) {
      output += n.getName() + ",";
    }
    output = output.substring(0, output.length()-1);
    output += "])";
    return output;
  }
}
