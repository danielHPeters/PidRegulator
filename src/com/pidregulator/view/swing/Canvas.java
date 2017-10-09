package com.pidregulator.view.swing;

import com.pidregulator.control.PidData;
import com.pidregulator.control.State;

import com.pidregulator.interfaces.ICanvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Paints all the data should have no logic and data manipulation inside if possible.
 *
 * @author t.gronowski, Daniel Peters
 */
public class Canvas extends JPanel implements ICanvas {
  private final int windowHeight = 500;
  private final int scaleX = 5;
  private final int scaleY = 50;
  private final PidData data;
  private final State state;

  /**
   * Default constructor. Initializes the reference to the data object, loads
   * the aeroplane image and sets the default anomaly.
   *
   * @param data  pid data
   * @param state draw state
   */
  public Canvas(PidData data, State state) {
    this.data = data;
    this.state = state;
    init();
  }

  private void init() {
    setBackground(Color.blue);
    setForeground(Color.white);
    setBounds(0, 40, 800, 800);
  }

  /**
   * Draws the ist lines up to the current state.
   *
   * @param g the graphics context object
   */
  private void drawActual(Graphics2D g) {
    int x;
    int y;
    int altX;
    int altY;
    int previous;
    int current;

    x = 0;
    altX = 0;
    current = 0;

    for (int i = 1; i < state.getState(); i++) {
      // Anomaly eg. plasma storm
      if (i == 50) {
        data.getActual().set(i, state.getAnomaly());
      }

      previous = (int) (scaleY * data.getActual().get(i - 1));
      current = (int) (scaleY * data.getActual().get(i));
      x = i * scaleX;
      altY = windowHeight - previous;
      y = windowHeight - current;

      g.drawLine(altX, altY, x, y);

      altX = x;
    }

    g.drawImage(data.getPlane(), x, windowHeight - current, this);
  }

  /**
   * Draws the complete target line.
   *
   * @param g the graphics context object
   */
  private void drawTarget(Graphics2D g) {
    int x;
    int y;
    int altX;
    int altY;

    altX = 0;

    for (int j = 1; j < data.getTarget().size(); j++) {
      x = j * scaleX;
      altY = windowHeight - (int) (scaleY * data.getTarget().get(j - 1));
      y = windowHeight - (int) (scaleY * data.getTarget().get(j));

      g.drawLine(altX, altY, x, y);
      altX = x;
    }
  }

  /**
   * Main painting method drawing all the lines as well as the title.
   *
   * @param g the graphics context object
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.white);
    g2.drawString("Canvas", 20, 20);
    g2.setColor(Color.yellow);
    drawTarget(g2);
    g2.setColor(Color.red);
    drawActual(g2);
  }

  @Override
  public void draw() {
    repaint();
  }
}
