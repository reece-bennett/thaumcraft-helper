package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

  public MainFrame() {
    initUI();
  }
  
  private void initUI() {
    setTitle("Thaumcraft Helper");
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    
    add(new Sidebar(), BorderLayout.CENTER);
    
    pack();
  }

  public static void main(String[] args) {
    
    EventQueue.invokeLater(new Runnable() {
      
      @Override
      public void run() {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
      }
    });
  }
}
