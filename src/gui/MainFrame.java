package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import eclipsesource.json.Json;
import eclipsesource.json.JsonArray;
import eclipsesource.json.JsonObject;
import eclipsesource.json.JsonObject.Member;
import graph.Graph;
import graph.Node;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

  private Graph graph;
  private LinkedHashMap<String, String[]> aspectMap;
  private ImageStore imageStore;

  public MainFrame() {
    aspectMap = new LinkedHashMap<>();
    imageStore = new ImageStore();
    initGraph();
    initUI();
  }

  private void initGraph() {
    // Read the Json
    JsonObject aspects = null;
    try {
      aspects = Json.parse(new InputStreamReader(getClass().getResourceAsStream("/graph/Aspects.json"))).asObject();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Reset the graph
    graph = new Graph();

    // Iterate through all aspects and put them into the graph
    for (Member aspect : aspects) {
      String name = aspect.getName();
      JsonArray list = aspect.getValue().asArray();

      graph.add(new Node(name));

      if (list.size() == 0) {
        aspectMap.put(name, null);
      } else if (list.size() == 2) {
        String[] connections = new String[2];
        for (int i = 0; i < 2; i++) {
          graph.connect(aspect.getName(), list.get(i).asString());
          connections[i] = list.get(i).asString();
        }
        aspectMap.put(name, connections);
      } else {
        System.err.println("List for " + name + " is of length " + list.size());
      }
    }
  }

  private void initUI() {
    // Give the JFrame an inset of 10px
    JPanel contentPanel = new JPanel();
    contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setContentPane(contentPanel);

    setTitle("Thaumcraft Helper");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Setting the minimum size helps expand the currently empty output pane
    setMinimumSize(new Dimension(600, 0));

    OutputPanel outputPanel = new OutputPanel(this);
    JScrollPane scrollPane = new JScrollPane(outputPanel);
    add(scrollPane, BorderLayout.CENTER);
    // Force the scroll bar to always show
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    // The SmartScroller makes the scrollPane always scroll to the end when new paths are added
    new SmartScroller(scrollPane, SmartScroller.HORIZONTAL, SmartScroller.END);

    Sidebar sidebar = new Sidebar(this, outputPanel);
    add(sidebar, BorderLayout.LINE_START);

    pack();
    setLocationRelativeTo(null);
  }

  public static void setLookAndFeel(String name) {
    try {

      LookAndFeelInfo[] installed = UIManager.getInstalledLookAndFeels();
      for (LookAndFeelInfo info : installed) {
        // System.out.println(info);
        if (info.getName().contains(name)) {
          UIManager.setLookAndFeel(info.getClassName());
        }
      }

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    EventQueue.invokeLater(new Runnable() {

      @Override
      public void run() {
        setLookAndFeel("Nimbus");
        // WebLookAndFeel.install();

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
      }
    });
  }

  public Graph getGraph() {
    return graph;
  }

  public LinkedHashMap<String, String[]> getAspects() {
    return aspectMap;
  }
  
  public Image getImage(String name) {
    return imageStore.getImage(name);
  }
}
