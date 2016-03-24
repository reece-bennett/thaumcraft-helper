package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import graph.Graph;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
  
  private Graph graph;

  public ButtonPanel(Graph graph) {
    this.graph = graph;
    initUI();
  }
  
  private void initUI() {
    setPreferredSize(new Dimension(600, 400));
    for (String aspect : graph.getAll().keySet()) {
      JButton button = new JButton(aspect);
      button.setFocusable(false);
      add(button);
    }
  }
}
