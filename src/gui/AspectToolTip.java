package gui;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

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
    gl.setAutoCreateGaps(false);
    
    JLabel aspectIcon = new JLabel(new ImageIcon(mainFrame.getImage(aspectName)));
    JLabel name = new JLabel(aspectName.substring(0, 1).toUpperCase() + aspectName.substring(1));
    String t = mainFrame.translate(aspectName);
    JLabel translation = new JLabel(t.substring(0, 1).toUpperCase() + t.substring(1));
    
    JLabel equals = new JLabel("=");
    JLabel plus = new JLabel("+");
    
    // Make the enum a little bit shorter
    LayoutStyle.ComponentPlacement r = LayoutStyle.ComponentPlacement.RELATED;
    
    String[] ingredients = mainFrame.getIngredients().get(aspectName);
    if (ingredients == null) {
      gl.setHorizontalGroup(gl.createSequentialGroup()
          .addComponent(aspectIcon).addPreferredGap(r)
          .addGroup(gl.createParallelGroup()
              .addComponent(name)
              .addComponent(translation)));
      
      gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
          .addComponent(aspectIcon)
          .addGroup(gl.createSequentialGroup()
              .addComponent(name)
              .addComponent(translation)));
    } else {
      JLabel part1 = new JLabel(new ImageIcon(mainFrame.getImage(ingredients[0])));
      JLabel part1Name = new JLabel(ingredients[0].substring(0, 1).toUpperCase() + ingredients[0].substring(1));
      String t1 = mainFrame.translate(ingredients[0]);
      JLabel translation1 = new JLabel(t1.substring(0, 1).toUpperCase() + t1.substring(1));
      
      JLabel part2 = new JLabel(new ImageIcon(mainFrame.getImage(ingredients[1])));
      JLabel part2Name = new JLabel(ingredients[1].substring(0, 1).toUpperCase() + ingredients[1].substring(1));
      String t2 = mainFrame.translate(ingredients[1]);
      JLabel translation2 = new JLabel(t2.substring(0, 1).toUpperCase() + t2.substring(1));
      
      gl.setHorizontalGroup(gl.createSequentialGroup()
          .addComponent(aspectIcon).addPreferredGap(r)
          .addGroup(gl.createParallelGroup()
          .addComponent(name)
          .addComponent(translation)).addPreferredGap(r)
          .addComponent(equals).addPreferredGap(r)
          .addComponent(part1).addPreferredGap(r)
          .addGroup(gl.createParallelGroup()
          .addComponent(part1Name)
          .addComponent(translation1)).addPreferredGap(r)
          .addComponent(plus).addPreferredGap(r)
          .addComponent(part2).addPreferredGap(r)
          .addGroup(gl.createParallelGroup()
          .addComponent(part2Name)
          .addComponent(translation2)));
      
      gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
          .addComponent(aspectIcon)
          .addGroup(gl.createSequentialGroup()
          .addComponent(name)
          .addComponent(translation))
          .addComponent(equals)
          .addComponent(part1)
          .addGroup(gl.createSequentialGroup()
          .addComponent(part1Name)
          .addComponent(translation1))
          .addComponent(plus)
          .addComponent(part2)
          .addGroup(gl.createSequentialGroup()
          .addComponent(part2Name)
          .addComponent(translation2)));
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
