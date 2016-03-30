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

  public AspectToolTip(String aspectName) {
    this.aspectName = aspectName;
    x = 0;
    y = 0;
    width = 100;
    height = 100;
    initUI();
  }
  
  private void initUI() {
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
    
    JLabel name = new JLabel(aspectName);
    
    gl.setHorizontalGroup(gl.createSequentialGroup()
        .addComponent(aspectIcon)
        .addComponent(name));
    
    gl.setVerticalGroup(gl.createParallelGroup()
        .addGroup(gl.createSequentialGroup())
          .addComponent(aspectIcon)
          .addComponent(name));
    
    width = getPreferredSize().width;
    height = getPreferredSize().height;
  }
  
  public void setPos(int x, int y) {
    this.x = x;
    this.y = y;
    setBounds(x, y, width, height);
  }
}
