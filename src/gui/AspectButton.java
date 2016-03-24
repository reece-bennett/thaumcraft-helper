package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class AspectButton extends JButton implements MouseListener {

  // States can be 0: not selected, 1: start aspect, 2: end aspect
  private int state;
  private String aspect;

  public AspectButton(String aspect) {
    super();
    state = 0;
    this.aspect = aspect;
    initButton();
  }

  private void initButton() {
    setBorder(makePadding(4));
    setFocusable(false);

    addMouseListener(this);

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
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (SwingUtilities.isRightMouseButton(e)) {
      getModel().setArmed(true);
      getModel().setPressed(true);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      System.out.println("Left click on " + aspect);
    } else if (SwingUtilities.isRightMouseButton(e)) {
      System.out.println("Right click on " + aspect);
      getModel().setPressed(false);
    }
  }
}
