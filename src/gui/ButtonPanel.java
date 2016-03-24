package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
      AspectButton button = new AspectButton(aspect);
      add(button);
    }
  }
}
