package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class AspectButton extends JButton implements MouseListener, MouseMotionListener {

  // States can be 0: not selected, 1: start aspect, 2: end aspect
  private int state;
  private String aspect;
  private ButtonPanel panel;
  private JLayeredPane lp;
  private AspectToolTip toolTip;
  private MainFrame mainFrame;

  public AspectButton(String aspect, ButtonPanel panel, MainFrame mainFrame) {
    super();
    state = 0;
    this.aspect = aspect;
    this.panel = panel;
    this.lp = mainFrame.getLayeredPane();
    this.mainFrame = mainFrame;
    toolTip = new AspectToolTip(mainFrame, aspect);
    initButton();
  }

  private void initButton() {
    setBorder(makePadding(4));
    setFocusable(false);

    addMouseListener(this);
    addMouseMotionListener(this);

    try {
      Image img = ImageIO.read(getClass().getResource("/aspectIcons/" + aspect + ".png"));
      setIcon(new ImageIcon(img));
    } catch (IOException | IllegalArgumentException e) {
      System.err.println("Aspect icon for " + aspect + " not found: " + e.getMessage());
    }
  }
  
  private Border makePadding(int width) {
    return BorderFactory.createEmptyBorder(width, width, width, width);
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    switch (state) {
      case 0:
        this.state = state;
        setBorder(makePadding(4));
        break;
      case 1:
        this.state = state;
        setBorder(
            BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), makePadding(2)));
        break;
      case 2:
        this.state = state;
        setBorder(
            BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 0, 255), 2), makePadding(2)));
        break;
      default:
        System.err.println("Unknown state (" + state + ") for button " + aspect);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    lp.add(toolTip, 100);
  }

  @Override
  public void mouseExited(MouseEvent e) {
    lp.remove(toolTip);
    lp.repaint();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (SwingUtilities.isRightMouseButton(e)) {
      getModel().setArmed(true);
      getModel().setPressed(true);
    }
    // Keeps the tool-tip on top of the button
    lp.repaint();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      System.out.println("Left click on " + aspect);
      panel.setState(this, 1);
    } else if (SwingUtilities.isRightMouseButton(e)) {
      System.out.println("Right click on " + aspect);
      getModel().setPressed(false);
      panel.setState(this, 2);
    }
    // Keeps the tool-tip on top of the button
    lp.repaint();
  }

  public String getAspect() {
    return aspect;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    Point p = SwingUtilities.convertPoint(this, e.getX(), e.getY(), mainFrame);
    toolTip.setPos((int) p.getX() + 4, (int) p.getY() - 38);
  }
}
