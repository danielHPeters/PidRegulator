package ch.peters.daniel.pidcontroller.model;

/**
 * Int point class to store location data.
 *
 * @param <T>
 * @author Daniel
 * @version 1.0
 */
public class Point<T extends Number> {
  private T x;
  private T y;

  public Point(T x, T y) {
    this.x = x;
    this.y = y;
  }

  public T getX() {
    return x;
  }

  public T getY() {
    return y;
  }

  public void set(T x, T y) {
    this.x = x;
    this.y = y;
  }

  public void setX(T x) {
    this.x = x;
  }

  public void setY(T y) {
    this.y = y;
  }
}
