package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graph.Graph;
import graph.Node;

@SuppressWarnings("serial")
public class Sidebar extends JPanel implements ActionListener {

  private Graph graph;
  private ButtonPanel bPanel;
  private JComboBox<Integer> stepsB;
  private OutputPanel outputPanel;

  public Sidebar(MainFrame mainFrame, OutputPanel outputPanel) {
    this.graph = mainFrame.getGraph();
    this.outputPanel = outputPanel;
    initUI(mainFrame);
  }

  private void initUI(MainFrame mainFrame) {
    GroupLayout gl = new GroupLayout(this);
    setLayout(gl);
    gl.setAutoCreateGaps(true);
    setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

    JLabel stepsL = new JLabel("Steps:");
    stepsB = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });

    JButton goB = new JButton("Find connection");
    goB.addActionListener(this);

    bPanel = new ButtonPanel(mainFrame);

    gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(gl.createSequentialGroup().addComponent(stepsL).addComponent(stepsB).addComponent(goB))
        .addComponent(bPanel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

    gl.setVerticalGroup(gl.createSequentialGroup().addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(stepsL).addComponent(stepsB).addComponent(goB)).addComponent(bPanel));
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    System.out.println("Finding path between " + bPanel.getFirst() + " and " + bPanel.getSecond() + " with "
        + stepsB.getSelectedItem());
    LinkedList<Node> path = graph.getPath(bPanel.getFirst(), bPanel.getSecond(), stepsB.getSelectedIndex());
    System.out.println(path);
    outputPanel.addPath(path);
  }
}
