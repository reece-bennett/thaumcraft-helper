package gui;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AspectToolTip extends JPanel {

  private String aspectName;
  private int x;
  private int y;
  private int width;
  private int height;

  public AspectToolTip(MainFrame mainFrame, String aspectName) {
    this.aspectName = aspectName;
    x = 0;
    y = 0;
    width = 100;
    height = 100;
    initUI(mainFrame);
  }
  
  private void initUI(MainFrame mainFrame) {
    setBackground(new Color(200, 200, 200, 230));

    GroupLayout gl = new GroupLayout(this);
    setLayout(gl);
    gl.setAutoCreateContainerGaps(true);
    gl.setAutoCreateGaps(true);
    
    JLabel aspectIcon = null;
    try {
      Image img = ImageIO.read(getClass().getResource("/aspectIcons/" + aspectName + ".png"));
      aspectIcon = new JLabel(new ImageIcon(img));
    } catch (IOException | IllegalArgumentException e) {
      System.err.println("Aspect icon for " + aspectName + " not found: " + e.getMessage());
    }
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
      JLabel part1 = null;
      JLabel part1Name = new JLabel(madeOf[0].substring(0, 1).toUpperCase() + madeOf[0].substring(1));
      JLabel part2 = null;
      JLabel part2Name = new JLabel(madeOf[1].substring(0, 1).toUpperCase() + madeOf[1].substring(1));
      try {
        Image img = ImageIO.read(getClass().getResource("/aspectIcons/" + madeOf[0] + ".png"));
        part1 = new JLabel(new ImageIcon(img));
      } catch (IOException | IllegalArgumentException e) {
        System.err.println("Aspect icon for " + madeOf[0] + " not found: " + e.getMessage());
      }
      try {
        Image img = ImageIO.read(getClass().getResource("/aspectIcons/" + madeOf[1] + ".png"));
        part2 = new JLabel(new ImageIcon(img));
      } catch (IOException | IllegalArgumentException e) {
        System.err.println("Aspect icon for " + madeOf[1] + " not found: " + e.getMessage());
      }
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
    this.x = x;
    this.y = y;
    setBounds(x, y, width, height);
  }
}
