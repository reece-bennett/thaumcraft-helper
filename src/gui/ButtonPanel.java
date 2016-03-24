package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import graph.Graph;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
  
  private Graph graph;
  private ArrayList<AspectButton> buttons;
  
  private AspectButton first;
  private AspectButton second;

  public ButtonPanel(Graph graph) {
    this.graph = graph;
    buttons = new ArrayList<>();
    initUI();
  }
  
  private void initUI() {
    setPreferredSize(new Dimension(225, 500));
    
    for (String aspect : graph.getAll().keySet()) {
      AspectButton button = new AspectButton(aspect);
      add(button);
      buttons.add(button);
      
      // Assign the first two buttons to be first and second
      if (first == null) {
        first = button;
        button.setState(1);
      } else if (second == null) {
        second = button;
        button.setState(2);
      }
    }
  }
}
