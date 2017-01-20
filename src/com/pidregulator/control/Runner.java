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
     * Reference to the object containing lists of data to be represented as
     * lines.
     */
    private final PidData data;

    /**
     * Flag for checking if the program has drawn all lines
     */
    private boolean running;

    /**
     * Delay of the painting thread Prevents the lines to be instantly drawn on
     * program start.
     *
     */
    private int delay;

    /**
     * Default constructor Initializes the references to the PaintSurface and
     * PidData objects. Also sets a default delay and starts the painting loop.
     *
     * @param surface
     * @param data
     */
    public Runner(PaintSurface surface, PidData data) {
        this.surface = surface;
        this.data = data;
        this.running = true;
        this.delay = 30;
    }

    /**
     * Getter for the delay of the thread
     *
     * @return the current delay
     */
    public int getDelay() {
        return this.delay;
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

        while (this.running) {

            if (this.surface.getState() > this.data.getSoll().size() - 1) {

                this.running = false;

            } else {

                this.surface.incrementState();

                try {

                    Thread.sleep(this.delay);

                } catch (InterruptedException e) {

                    System.out.println(e.getCause());

                }

                this.surface.repaint();

            }

        }

    }

    /**
     * Change the delay of the loop by delayDiff Sets limits to the delay
     * (longest: 100, shortest: 5)
     *
     * @param delayDiff ammount by which the delay will be modified
     */
    public void changeSpeed(int delayDiff) {

        if (this.delay + delayDiff > 0 && this.delay + delayDiff <= 100) {

            this.delay += delayDiff;

        }

    }

    /**
     * Resets the drawing state and starts drawing from starting point
     */
    public void restart() {
        this.surface.resetState();
        this.running = true;
    }

}
