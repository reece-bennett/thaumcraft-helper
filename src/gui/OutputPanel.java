package gui;

import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import graph.Node;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel {
  
  public OutputPanel() {
    initUI();
  }
  
  private void initUI() {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
  }
  
  public void addPath(LinkedList<Node> path) {
    PathPanel panel = new PathPanel(path, this);
    panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
    add(panel);
    revalidate();
  }
  
  public void removePath(PathPanel path) {
    remove(path);
    revalidate();
    repaint();
  }
}
