package graph;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import eclipsesource.json.Json;
import eclipsesource.json.JsonArray;
import eclipsesource.json.JsonObject;
import eclipsesource.json.JsonObject.Member;
import eclipsesource.json.JsonValue;

public class TestReadJson {

  public static void main(String[] args) {
    // Read the Json
    JsonObject aspects = null;
    try {
      aspects = Json.parse(new InputStreamReader(
          TestReadJson.class.getResourceAsStream("/graph/Aspects.json"))).asObject();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    Graph graph = new Graph();
    
    for (Member aspect : aspects) {
      graph.add(new Node(aspect.getName()));
      JsonArray list = aspect.getValue().asArray();
      for (JsonValue a : list) {
        graph.connect(aspect.getName(), a.asString());
      }
    }
    
    System.out.println(graph.getPath("vacuos", "motus", 3));
  }
}
