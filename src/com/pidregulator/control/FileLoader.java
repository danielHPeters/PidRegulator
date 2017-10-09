package com.pidregulator.control;

import com.pidregulator.interfaces.DataLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads data from a file.
 *
 * @author d.peters
 */
public class FileLoader implements DataLoader {
  private String fileName;

  public FileLoader() {
    this.fileName = "data/points.txt";
  }

  /**
   * Returns a list with the loaded data as doubles.
   *
   * @return the completed list
   */
  @Override
  public List<Double> load() {
    String line;
    List<Double> target = new ArrayList<>();

    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));

      while ((line = br.readLine()) != null) {
        target.add(Double.parseDouble(line.trim()));
      }
    } catch (IOException e) {
      System.out.println("Failed to open file: " + fileName);
    }
    return target;
  }
}
