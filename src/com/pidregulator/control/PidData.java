package com.pidregulator.control;

import com.pidregulator.interfaces.DataLoader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Holds lists of data generated on instantiation.
 *
 * @author t.gronowski, Daniel Peters
 * @version 1.2
 */
public class PidData {
  private BufferedImage plane;
  private final DataLoader loader;
  private final List<Double> target;
  private final List<Double> actual;
  private final List<Double> difference;
  private final double valP = 0.1;
  private final double valI = 0.3;
  private final double valD = 0.1;

  /**
   * Default constructor. Initializes the reference to the data loader object
   * and initializes all the data lists
   *
   * @param loader Reference to the data loading object
   */
  public PidData(DataLoader loader) {
    try {
      this.plane = ImageIO.read(new File("assets/plane.png"));
    } catch (IOException e) {
      System.out.println("Failed to load image.");
    }
    this.loader = loader;
    this.target = loader.load();
    this.actual = new ArrayList<>();
    this.difference = new ArrayList<>();

    // Fill lists temporarily to avoid null pointer exception
    // TODO fix code to avoid this
    target.forEach(s -> {
      actual.add(0.0);
      difference.add(0.0);
    });
    calcDiffAndActual();
  }

  public BufferedImage getPlane() {
    return plane;
  }

  public List<Double> getTarget() {
    return target;
  }

  public List<Double> getActual() {
    return actual;
  }

  public List<Double> getDifference() {
    return difference;
  }


  /**
   * Calculates new actual data.
   *
   * @param j the current index of the lists
   * @return the calculated actual
   */
  private double calcNewActual(int j) {
    double currentDiff;
    double newAct;

    currentDiff = difference.get(j - 1);
    newAct = actual.get(j - 1) + valP * +valD
        * (currentDiff - difference.get(j - 2)) + valI
        * (currentDiff + difference.get(j - 2) + difference.get(j - 3));

    return newAct;
  }

  /**
   * Calculates the difference and actual data from the target data and the PID
   * regulator values.
   */
  private void calcDiffAndActual() {
    int i;
    int j;
    double newAct;

    for (i = 1; i < target.size() - 2; i++) {
      j = i + 2;
      difference.set(j - 1, target.get(j - 1) - actual.get(j - 1));
      newAct = calcNewActual(j);
      actual.set(j, newAct);
    }
  }
}
