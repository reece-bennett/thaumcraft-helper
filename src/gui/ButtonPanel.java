package gui;

import java.awt.Dimension;
import java.awt.Image;
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
      JButton button = new JButton();

      try {
        Image img = ImageIO.read(getClass().getResource("/aspectIcons/" + aspect + ".png"));
        button.setIcon(new ImageIcon(img));
      } catch (IOException | IllegalArgumentException e) {
        System.err.println("Aspect icon for " + aspect + " not found: " + e.getMessage());
      }
      
      button.setFocusable(false);
      add(button);
    }
  }
}
