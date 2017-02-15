package com.pidregulator.control;

import com.pidregulator.model.DataLoader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Holds lists of data generated on instantiation
 *
 * @author t.gronowski, d.peters
 */
public class PidData {
    
    /**
     * Aeroplane image
     */
    private BufferedImage plane;

    /**
     * Reference to the object loading the data.
     */
    private final DataLoader loader;

    /**
     * List containing the soll data.
     */
    private final List<Double> soll;

    /**
     * List containing the ist data.
     */
    private final List<Double> ist;

    /**
     * List containing the difference data
     */
    private final List<Double> diff;

    /**
     * The P regulator value
     */
    private final double K_P = 0.1;

    /**
     * The I regulator value
     */
    private final double K_I = 0.3;

    /**
     * The D regulator value
     */
    private final double K_D = 0.1;

    /**
     * Default constructor. Initializes the reference to the data loader object
     * and initializes all the data lists
     *
     * @param loader Reference to the data loading object
     */
    public PidData(DataLoader loader) {
        try {

            this.plane = ImageIO.read(new File("flugzeug.png"));

        } catch (IOException e) {
            System.out.println("Failed to load image.");
        }
        this.loader = loader;
        this.soll = loader.load();
        this.ist = new ArrayList<>();
        this.diff = new ArrayList<>();
        /** 
         * Temporarily fill with values to avoid nullpointer errors.
         * This fix should be removed, when the data generation algorithm has
         * been properly fixed and implemented
         */
        this.soll.forEach(s -> {
            this.ist.add(0.0);
            this.diff.add(0.0);
        });
        calcDiffAndIst();
    }

    /**
     * Getter for the soll list
     *
     * @return the soll list
     */
    public List<Double> getSoll() {
        return soll;
    }

    /**
     * Getter for the ist list
     *
     * @return the ist list
     */
    public List<Double> getIst() {
        return ist;
    }

    /**
     * Getter for the difference list
     *
     * @return the difference list
     */
    public List<Double> getDiff() {
        return diff;
    }

    /**
     * 
     * @return 
     */
    public BufferedImage getPlane() {
        return plane;
    }
    

    /**
     * Calculates new ist data
     *
     * @param j the current index of the lists
     * @return the calculated ist
     */
    public double calcNewIst(int j) {
        double currentDiff, newIst;

        currentDiff = this.diff.get(j - 1);
        newIst = this.ist.get(j - 1) + this.K_P * +this.K_D
                * (currentDiff - this.diff.get(j - 2)) + this.K_I
                * (currentDiff + this.diff.get(j - 2) + this.diff.get(j - 3));

        return newIst;
    }

    /**
     * Calculates the difference and ist data from the soll data and the PID
     * regulator values
     */
    private void calcDiffAndIst() {
        int i, j;
        double newIst;

        for (i = 1; i < this.soll.size() - 2; i++) {

            j = i + 2;
            this.diff.set(j - 1, this.soll.get(j - 1) - this.ist.get(j - 1));
            newIst = calcNewIst(j);
            this.ist.set(j, newIst);

        }
    }
}
