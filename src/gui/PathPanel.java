package gui;

import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graph.Node;

@SuppressWarnings("serial")
public class PathPanel extends JPanel {
  
  private LinkedList<Node> path;

  public PathPanel(LinkedList<Node> path) {
    this.path = path;
    initUI();
  }
  
  private void initUI() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    add(new JLabel(path.getFirst().getName() + " \u2192 " + path.getLast().getName()));
    
    for (Node n : path) {
      add(new JLabel(n.getName()));
      if (path.getLast() != n) {
        add(new JLabel("\u2193"));
      }
    }
  }
}
