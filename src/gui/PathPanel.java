package gui;

import java.awt.Color;
import java.awt.Component;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
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
    GroupLayout gl = new GroupLayout(this);
    setLayout(gl);
    gl.setAutoCreateGaps(true);
    gl.setAutoCreateContainerGaps(true);
    
    setBorder(BorderFactory.createLineBorder(new Color(170, 170, 180)));
    
    JLabel header = new JLabel(path.getFirst().getName() + " \u2192 " + path.getLast().getName());
    
    JButton close = new JButton("X");
    
    JPanel pathContainer = new JPanel();
    pathContainer.setLayout(new BoxLayout(pathContainer, BoxLayout.Y_AXIS));
    
    for (Node n : path) {
      JLabel aspect = new JLabel(n.getName());
      aspect.setAlignmentX(Component.CENTER_ALIGNMENT);
      pathContainer.add(aspect);
      
      if (path.getLast() != n) {
        JLabel arrow = new JLabel("\u2193");
        arrow.setAlignmentX(Component.CENTER_ALIGNMENT);
        pathContainer.add(arrow);
      }
    }
    
    gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addGroup(gl.createSequentialGroup()
            .addComponent(header)
            .addComponent(close))
        .addComponent(pathContainer));
    
    gl.setVerticalGroup(gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(header)
            .addComponent(close))
        .addComponent(pathContainer));
  }
}
