package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Sidebar extends JPanel {

  private JComboBox<String> startB;
  private JComboBox<String> endB;
  private JComboBox<Integer> stepsB;

  public Sidebar() {
    initUI();
  }

  private void initUI() {
    GroupLayout gl = new GroupLayout(this);
    setLayout(gl);
    gl.setAutoCreateGaps(true);
    gl.setAutoCreateContainerGaps(true);

    JLabel startL = new JLabel("Start:");
    startB = new JComboBox<>(new String[] { "Aer", "Ordo", "Aqua", "Terra" });
    startB.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          System.out.println("Start: " + startB.getSelectedItem());
        }
      }
    });
    AutoCompletion.enable(startB);

    JLabel endL = new JLabel("End:");
    endB = new JComboBox<>(new String[] { "Aer", "Ordo", "Aqua", "Terra" });
    endB.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          System.out.println("End: " + endB.getSelectedItem());
        }
      }
    });
    AutoCompletion.enable(endB);

    JLabel stepsL = new JLabel("Steps:");
    stepsB = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
    stepsB.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          System.out.println("Step: " + stepsB.getSelectedItem());
        }
      }
    });

    gl.setHorizontalGroup(gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(startL).addComponent(endL)
            .addComponent(stepsL))
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(startB).addComponent(endB)
            .addComponent(stepsB)));

    gl.setVerticalGroup(gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(startL).addComponent(startB))
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(endL).addComponent(endB))
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(stepsL).addComponent(stepsB)));
  }
}
