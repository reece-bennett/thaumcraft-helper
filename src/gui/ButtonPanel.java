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
      AspectButton button = new AspectButton(aspect, this);
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
  
  public void setState(AspectButton button, int state) {
    if (state == 1) {
      first.setState(0);
      first = button;
    } else if (state == 2) {
      second.setState(0);
      second = button;
    } else if (state == 0 && first == button) {
      first = null;
    } else if (state == 0 && second == button) {
      second = null;
    }
    button.setState(state);
  }
  
  public String getFirst() {
    return first.getAspect();
  }
  
  public String getSecond() {
    return second.getAspect();
  }
}
