package ch.peters.daniel.pidcontroller.control;

import ch.peters.daniel.pidcontroller.interfaces.DataHandler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Holds lists of data generated on instantiation.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class PidData {
  private BufferedImage plane;
  private List<Double> target;
  private List<Double> actual;
  private List<Double> difference;

  /**
   * Default constructor. Initializes the reference to the data loader object
   * and initializes all the data lists
   *
   * @param loader Reference to the data loading object
   */
  public PidData(DataHandler<Double> loader) {
    try {
      this.plane = ImageIO.read(getClass().getResourceAsStream("/img/plane.png"));

      this.target = loader.get();
      this.actual = new ArrayList<>();
      this.difference = new ArrayList<>();

      // Fill lists temporarily to avoid null pointer exception
      // TODO fix code to avoid this
      target.forEach(s -> {
        actual.add(0.0);
        difference.add(0.0);
      });
      calcDiffAndActual();
    } catch (IOException e) {
      System.out.println("Failed to get image.");
    }
  }

  /**
   * Calculates new actual data.
   *
   * @param j the current index of the lists
   * @return the calculated actual
   */
  private double calcNewActual(int j) {
    var currentDiff = difference.get(j - 1);
    var valP = 0.1;
    var valI = 0.3;
    var valD = 0.1;

    return actual.get(j - 1) + valP * +valD
        * (currentDiff - difference.get(j - 2)) + valI
        * (currentDiff + difference.get(j - 2) + difference.get(j - 3));
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
}
