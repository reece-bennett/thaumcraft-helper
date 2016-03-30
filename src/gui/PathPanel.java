package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import graph.Node;

@SuppressWarnings("serial")
public class PathPanel extends JPanel implements ActionListener {

  private LinkedList<Node> path;
  private OutputPanel outputPanel;
  private JLayeredPane lp;

  public PathPanel(MainFrame mainFrame, LinkedList<Node> path, OutputPanel outputPanel) {
    this.path = path;
    this.outputPanel = outputPanel;
    this.lp = mainFrame.getLayeredPane();
    initUI(mainFrame);
  }

  private void initUI(MainFrame mainFrame) {
    GroupLayout gl = new GroupLayout(this);
    setLayout(gl);
    gl.setAutoCreateGaps(true);
    gl.setAutoCreateContainerGaps(true);

    setBorder(BorderFactory.createLineBorder(new Color(170, 170, 180)));

    String start = path.getFirst().getName();
    start = start.substring(0, 1).toUpperCase() + start.substring(1);
    String end = path.getLast().getName();
    end = end.substring(0, 1).toUpperCase() + end.substring(1);
    JLabel header = new JLabel(start + " \u2192 " + end);
    header.setFont(header.getFont().deriveFont(Font.BOLD));

    JButton close = new JButton("\u2715");
    close.addActionListener(this);
    close.setBorder(BorderFactory.createEmptyBorder(6, 9, 6, 9));

    JPanel pathContainer = new JPanel();
    pathContainer.setLayout(new BoxLayout(pathContainer, BoxLayout.Y_AXIS));

    JLabel arrow = null;

    for (Node n : path) {
      JLabel aspect = new JLabel();
      AspectToolTip toolTip = new AspectToolTip(n.getName());
      aspect.addMouseListener(new MouseListener() {
        
        @Override
        public void mouseReleased(MouseEvent arg0) {
        }
        
        @Override
        public void mousePressed(MouseEvent arg0) {
        }
        
        @Override
        public void mouseEntered(MouseEvent arg0) {
          lp.add(toolTip, 100);
        }
        
        @Override
        public void mouseExited(MouseEvent arg0) {
          lp.remove(toolTip);
          lp.repaint();
        }
        
        @Override
        public void mouseClicked(MouseEvent arg0) {
        }
      });
      aspect.addMouseMotionListener(new MouseMotionListener() {
        
        @Override
        public void mouseMoved(MouseEvent e) {
          Point p = SwingUtilities.convertPoint(aspect, e.getX(), e.getY(), mainFrame);
          toolTip.setPos((int) p.getX() + 4, (int) p.getY() - 38);
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
        }
      });
      try {
        Image img = ImageIO.read(getClass().getResource("/aspectIcons/" + n.getName() + ".png"));
        aspect.setIcon(new ImageIcon(img));
      } catch (IOException | IllegalArgumentException e) {
        System.err.println("Aspect icon for " + n.getName() + " not found: " + e.getMessage());
      }
      aspect.setAlignmentX(Component.CENTER_ALIGNMENT);
      pathContainer.add(aspect);

      arrow = new JLabel("\u2193");
      arrow.setAlignmentX(Component.CENTER_ALIGNMENT);
      pathContainer.add(arrow);
    }
    // Remove the trailing arrow
    pathContainer.remove(arrow);

    gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addGroup(gl.createSequentialGroup().addComponent(header).addComponent(close)).addComponent(pathContainer));

    gl.setVerticalGroup(gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(header).addComponent(close))
        .addComponent(pathContainer));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    outputPanel.removePath(this);
    revalidate();
  }
}
