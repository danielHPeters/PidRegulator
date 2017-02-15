package com.pidregulator.control;

import com.pidregulator.view.PaintSurface;

/**
 * This runnable starts and runs the painting process.
 *
 * @author d.peters
 */
public class Runner implements Runnable {

    /**
     * Reference to JPanel object painting the lines
     */
    private final PaintSurface surface;

    /**
     * Drawing state
     */
    private final State state;

    /**
     * Default constructor Initializes the references to the PaintSurface and
     * PidData objects. Also sets a default delay and starts the painting loop.
     *
     * @param surface
     * @param state
     */
    public Runner(PaintSurface surface, State state) {
        this.surface = surface;
        this.state = state;
    }

    /**
     * Loop that increses the drawing state by 1 each time and invokes the
     * repaint funtion of the JPanel The checks prevent the loop from looping
     * out of bounds from the data lists and stops running the loop after all
     * lines have been drawn
     *
     */
    @Override
    public void run() {

        while (this.state.isRunning()) {

            if (this.state.getState() > this.state.getData().getSoll().size() - 1) {

                this.state.setRunning(false);

            } else {

                this.state.incrementState();

                try {

                    Thread.sleep(this.state.getDelay());

                } catch (InterruptedException e) {

                    System.out.println(e.getCause());

                }

                this.surface.repaint();

            }

        }

    }

}
