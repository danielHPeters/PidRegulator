package ch.peters.daniel.pidcontroller.control;

import ch.peters.daniel.pidcontroller.interfaces.ICanvas;

/**
 * This runnable starts and runs the painting process.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class Runner implements Runnable {
  private final ICanvas canvas;
  private final State state;

  /**
   * Default constructor Initializes the references to the Canvas and
   * PidData objects. Also sets a default delay and starts the painting loop.
   *
   * @param canvas reference to canvas
   * @param state  reference to drawing state
   */
  public Runner(ICanvas canvas, State state) {
    this.canvas = canvas;
    this.state = state;
  }

  /**
   * Loop that increases the drawing state by 1 each time and invokes the
   * repaint function of the JPanel. The checks prevent the loop from looping
   * out of bounds from the data lists and stops running the loop after all
   * lines have been drawn.
   */
  @Override
  public void run() {
    while (state.isRunning()) {
      if (state.getState() > state.getData().getTarget().size() - 1) {
        state.setRunning(false);
      } else {
        state.incrementState();

        try {
          Thread.sleep(state.getDelay());
        } catch (InterruptedException e) {
          System.out.println("Internal error.");
        }

        canvas.draw();
      }
    }
  }
}
