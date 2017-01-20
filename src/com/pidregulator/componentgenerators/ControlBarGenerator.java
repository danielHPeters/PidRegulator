package com.pidregulator.componentgenerators;

import com.pidregulator.view.PaintSurface;
import com.pidregulator.control.Runner;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author d.peters
 */
public class ControlBarGenerator {

    /**
     *
     */
    private final Runner run;

    /**
     *
     */
    private final PaintSurface surface;

    /**
     * Default contructor
     *
     * @param run reference to the runnable object
     * @param surface reference to the painting object
     */
    public ControlBarGenerator(Runner run, PaintSurface surface) {
        this.run = run;
        this.surface = surface;
    }

    /**
     * Generates a controlBar
     *
     * @return
     */
    public JPanel getNewControlBar() {

        JPanel controls = new JPanel();

        // Create and modify the anomaly text field
        JTextField messageArea = new JTextField();

        messageArea.setColumns(10);
        messageArea.setEditable(false);

        // Create and modify the speed display
        JTextField speedDisplay = new JTextField();

        speedDisplay.setColumns(10);
        speedDisplay.setEditable(false);
        speedDisplay.setText(Integer.toString((105 - this.run.getDelay())));

        // Get Button Generators
        AnomalyButtonGenerator btnGenerator = new AnomalyButtonGenerator(
                this.surface, messageArea);
        ControlButtonGenerator controlsBtnGen = new ControlButtonGenerator(
                this.run, speedDisplay);

        controls.setBounds(0, 0, 800, 40);
        controls.setBackground(Color.yellow);

        // Generate and add buttons to controlBar
        JButton north = btnGenerator.generateButton("Norden", 5);
        JButton south = btnGenerator.generateButton("Süden", -5);
        JButton defaultAnom = btnGenerator.generateButton(" ", 0);
        JButton restartButton = controlsBtnGen.generateRestartButton();
        JButton fasterButton = controlsBtnGen.generateDelayButton("Schneller", -5);
        JButton slowerButton = controlsBtnGen.generateDelayButton("Langsamer", 5);

        controls.add(restartButton, BorderLayout.NORTH);
        controls.add(fasterButton, BorderLayout.NORTH);
        controls.add(slowerButton, BorderLayout.NORTH);
        controls.add(speedDisplay, BorderLayout.SOUTH);
        controls.add(north, BorderLayout.NORTH);
        controls.add(defaultAnom, BorderLayout.NORTH);
        controls.add(south, BorderLayout.NORTH);
        controls.add(messageArea, BorderLayout.SOUTH);

        return controls;
    }
}
