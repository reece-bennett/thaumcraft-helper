package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graph.Graph;

@SuppressWarnings("serial")
public class Sidebar extends JPanel implements ActionListener {

  private Graph graph;

  private ButtonPanel bPanel;

  public Sidebar(Graph graph) {
    this.graph = graph;
    initUI();
  }

  private void initUI() {
    GroupLayout gl = new GroupLayout(this);
    setLayout(gl);
    gl.setAutoCreateGaps(true);
    gl.setAutoCreateContainerGaps(true);

    JLabel stepsL = new JLabel("Steps:");
    JComboBox<Integer> stepsB = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
    
    JButton goB = new JButton("Find connection");
    goB.addActionListener(this);
    
    bPanel = new ButtonPanel(graph);
    
    gl.setHorizontalGroup(
        gl.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(gl.createSequentialGroup()
              .addComponent(stepsL)
              .addComponent(stepsB)
              .addComponent(goB))
          .addComponent(bPanel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    
    gl.setVerticalGroup(gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(stepsL)
            .addComponent(stepsB)
            .addComponent(goB))
        .addComponent(bPanel));
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    System.out.println("Finding path");
  }
}
