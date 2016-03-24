package gui;

import java.util.LinkedList;

import javax.swing.JPanel;

import graph.Node;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel {
  
  public OutputPanel() {
  }
  
  public void addPath(LinkedList<Node> path) {
    add(new PathPanel(path, this));
    revalidate();
  }
  
  public void removePath(PathPanel path) {
    remove(path);
    revalidate();
    repaint();
  }
}
