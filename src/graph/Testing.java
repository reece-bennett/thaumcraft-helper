package graph;

public class Testing {
  
  public static void main(String[] args) {
    
    Graph graph = new Graph();
    graph.add(new Node("A"));
    graph.add(new Node("B"));
    graph.add(new Node("C"));
    graph.add(new Node("D"));
    graph.add(new Node("E"));
    graph.add(new Node("F"));
    
    graph.connect("A", new String[] {"B", "C", "D"});
    graph.connect("B", new String[] {"C", "E"});
    //graph.connect("D", "E");
    graph.connect("E", "F");
    
    System.out.println(graph);
    
    System.out.println(graph.getPath("D", "F"));
  }
}
