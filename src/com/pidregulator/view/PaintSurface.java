package com.pidregulator.view;

import com.pidregulator.control.PidData;
import com.pidregulator.control.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Paints all the data should hava no logic and data manipulation inside if
 * possible
 *
 * @author t.gronowski, d.peters
 */
public class PaintSurface extends JPanel {

    /**
     * The constant height of the panel TODO: needs to be made flexible to allow
     * resizing of image needs quite a lot of work
     */
    private final int WINDOW_HEIGHT = 500;

    /**
     * Scaling of the x dimension
     */
    private final int SCALE_X = 5;

    /**
     * Scaling of the y dimension
     */
    private final int SCALE_Y = 50;

    /**
     * Reference to the object containing the data lists
     */
    private final PidData data;
    
    /**
     * The drawing state
     */
    private final State state;

    /**
     * Default constructor. Initializes the reference to the data object, loads
     * the aeroplane image and sets the default anomaly.
     *
     * @param data
     * @param state
     */
    public PaintSurface(PidData data, State state) {
        this.data = data;
        this.state = state;
    }

    /**
     * Draws the ist lines up to the current state
     *
     * @param g the graphics context object
     */
    public void zeichneIst(Graphics2D g) {
        int i, x, y, xAlt, yAlt, prevIst, currentIst;

        x = 0;
        xAlt = 0;
        currentIst = 0;

        for (i = 1; i < this.state.getState(); i++) {

            // Anomaly eg. plasmastorm
            if (i == 50) {
                this.data.getIst().set(i, this.state.getAnomaly());
            }

            prevIst = (int) (this.SCALE_Y * this.data.getIst().get(i - 1));
            currentIst = (int) (this.SCALE_Y * this.data.getIst().get(i));
            x = i * this.SCALE_X;
            yAlt = this.WINDOW_HEIGHT - prevIst;
            y = this.WINDOW_HEIGHT - currentIst;

            g.drawLine(xAlt, yAlt, x, y);

            xAlt = x;

        }

        g.drawImage(this.data.getPlane(), x, this.WINDOW_HEIGHT - currentIst, this);
    }

    /**
     * Draws the complete soll line
     *
     * @param g the graphics context object
     */
    public void zeichneSoll(Graphics2D g) {

        int x, y, xAlt, yAlt, j;

        xAlt = 0;

        for (j = 1; j < this.data.getSoll().size(); j++) {

            x = j * this.SCALE_X;
            yAlt = this.WINDOW_HEIGHT
                    - (int) (this.SCALE_Y * this.data.getSoll().get(j - 1));
            y = this.WINDOW_HEIGHT
                    - (int) (this.SCALE_Y * this.data.getSoll().get(j));

            g.drawLine(xAlt, yAlt, x, y);
            xAlt = x;

        }

    }

    /**
     * Main painting method drawing all the lines as well as the title.
     *
     * @param g the graphics context object
     */
    @Override
    public void paint(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);
        g2.drawString("Canvas", 20, 20);
        g2.setColor(Color.yellow);
        zeichneSoll(g2);
        g2.setColor(Color.red);
        zeichneIst(g2);

    }
}
