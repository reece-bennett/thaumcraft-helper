package gui;

import java.awt.Color;

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
    width = 50;
    height = 50;
    initUI();
  }
  
  private void initUI() {
    System.out.println(aspectName);
    setBackground(new Color(200, 200, 200));
    setBounds(x, x, width, height);
    JLabel label = new JLabel(aspectName);
    add(label);
  }
  
  public void setPos(int x, int y) {
    this.x = x;
    this.y = y;
    setBounds(x, y, width, height);
  }
}
