package gui;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AspectToolTip extends JPanel {

  private String aspectName;
  private int width;
  private int height;
  private MainFrame mainFrame;

  public AspectToolTip(MainFrame mainFrame, String aspectName) {
    this.aspectName = aspectName;
    this.mainFrame = mainFrame;
    initUI();
  }
  
  private void initUI() {
    setBackground(new Color(200, 200, 200, 200));

    GroupLayout gl = new GroupLayout(this);
    setLayout(gl);
    gl.setAutoCreateContainerGaps(true);
    gl.setAutoCreateGaps(true);
    
    JLabel aspectIcon = new JLabel(new ImageIcon(mainFrame.getImage(aspectName)));
    JLabel name = new JLabel(aspectName.substring(0, 1).toUpperCase() + aspectName.substring(1));
    
    JLabel equals = new JLabel("=");
    JLabel plus = new JLabel("+");
    
    String[] madeOf = mainFrame.getAspects().get(aspectName);
    if (madeOf == null) {
      gl.setHorizontalGroup(gl.createSequentialGroup()
          .addComponent(aspectIcon)
          .addComponent(name));
      
      gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
          .addComponent(aspectIcon)
          .addComponent(name));
    } else {
      JLabel part1 = new JLabel(new ImageIcon(mainFrame.getImage(madeOf[0])));
      JLabel part1Name = new JLabel(madeOf[0].substring(0, 1).toUpperCase() + madeOf[0].substring(1));
      
      JLabel part2 = new JLabel(new ImageIcon(mainFrame.getImage(madeOf[1])));
      JLabel part2Name = new JLabel(madeOf[1].substring(0, 1).toUpperCase() + madeOf[1].substring(1));
      
      gl.setHorizontalGroup(gl.createSequentialGroup()
          .addComponent(aspectIcon)
          .addComponent(name)
          .addComponent(equals)
          .addComponent(part1)
          .addComponent(part1Name)
          .addComponent(plus)
          .addComponent(part2)
          .addComponent(part2Name));
      
      gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
          .addComponent(aspectIcon)
          .addComponent(name)
          .addComponent(equals)
          .addComponent(part1)
          .addComponent(part1Name)
          .addComponent(plus)
          .addComponent(part2)
          .addComponent(part2Name));
    }
    
    width = getPreferredSize().width;
    height = getPreferredSize().height;
  }
  
  public void setPos(int x, int y) {
    int mainWidth = mainFrame.getWidth() - mainFrame.getInsets().left - mainFrame.getInsets().right;
    int mainHeight = mainFrame.getHeight() - mainFrame.getInsets().top - mainFrame.getInsets().bottom;
    
    if (x > mainWidth - width) {
      x = mainWidth - width; 
    }
    
    if (y > mainHeight - height) { 
      y = mainHeight - height; 
    }
    
    setBounds(x, y, width, height);
  }
}
