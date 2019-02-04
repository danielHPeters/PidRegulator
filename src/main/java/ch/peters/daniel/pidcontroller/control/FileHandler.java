package ch.peters.daniel.pidcontroller.control;

import ch.peters.daniel.pidcontroller.interfaces.DataHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Loads data from a file.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class FileHandler implements DataHandler<Double> {
  private String fileName;

  public FileHandler() {
    this.fileName = "/text/points.txt";
  }

  /**
   * Returns a list with the loaded data as doubles.
   *
   * @return The completed list
   */
  @Override
  public List<Double> get() {
    var values = new ArrayList<Double>();

    try (var br = Files.newBufferedReader(Paths.get(getClass().getResource(fileName).toURI()))) {
      br.lines()
        .map(line -> Double.parseDouble(line.trim()))
        .collect(Collectors.toCollection(() -> values));
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    return values;
  }
}
