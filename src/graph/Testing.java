package graph;

public class Testing {
  
  public static void main(String[] args) {
    
    Graph graph = new Graph();
    graph.add(new Node("A"));
    graph.add(new Node("B"));
    graph.add(new Node("C"));
    
    graph.connect("A", "B");
    
    System.out.println(graph);
    
  }
}
