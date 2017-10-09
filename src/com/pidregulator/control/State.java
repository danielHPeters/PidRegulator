package com.pidregulator.control;

/**
 * Drawing state to track till which point has already been drawn.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class State {

  /**
   * anomaly (deviation from the intended course of the plane) can be changed
   * by the anomaly buttons. TODO: move to somewhere where it makes sense,
   * because this class should only handle painting of objects
   */
  private double anomaly;

  /**
   * A state counter limiting the amount of lines to be drawn from the data
   * (makes the line to be drawn in real time instead of instantly on program
   * start).
   */
  private int state;
  private boolean running;
  /**
   * Delay of the painting thread Prevents the lines to be instantly drawn on
   * program start.
   */
  private int delay;
  private final PidData data;

  /**
   * Default constructor.
   *
   * @param data data source
   */
  public State(PidData data) {
    this.anomaly = 0;
    this.state = 0;
    this.running = true;
    this.delay = 30;
    this.data = data;
  }

  public double getAnomaly() {
    return anomaly;
  }

  public int getState() {
    return this.state;
  }

  public boolean isRunning() {
    return running;
  }

  public int getDelay() {
    return this.delay;
  }

  public PidData getData() {
    return data;
  }

  public void setAnomaly(double anomaly) {
    this.anomaly = anomaly;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  public void incrementState() {
    this.state++;
  }

  public void reset() {
    this.state = 0;
    this.running = true;
  }

  /**
   * Change the delay of the loop by delayDiff Sets limits to the delay
   * (longest: 100, shortest: 5).
   *
   * @param delayDiff ammount by which the delay will be modified
   */
  public void changeSpeed(int delayDiff) {
    if (this.delay + delayDiff > 0 && this.delay + delayDiff <= 100) {
      this.delay += delayDiff;
    }
  }
}
