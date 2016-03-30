package gui;

import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import graph.Node;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel {
  
  private MainFrame mainFrame;
  
  public OutputPanel(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
    initUI();
  }
  
  private void initUI() {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
  }
  
  public void addPath(LinkedList<Node> path) {
    PathPanel panel = new PathPanel(mainFrame, path, this);
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
