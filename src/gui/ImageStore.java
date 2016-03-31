package gui;

import java.awt.Image;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;

public class ImageStore {

  private LinkedHashMap<String, Image> aspectIcons;
  
  public ImageStore() {
    aspectIcons = new LinkedHashMap<>();
  }
  
  public Image getImage(String name) {
    if (!aspectIcons.containsKey(name)) {
      try {
        Image img = ImageIO.read(getClass().getResource("/aspectIcons/" + name + ".png"));
        aspectIcons.put(name, img);
      } catch (IOException | IllegalArgumentException e) {
        System.err.println("Aspect icon for " + name + " not found: " + e.getMessage());
      }
    }
    return aspectIcons.get(name);
  }
}
