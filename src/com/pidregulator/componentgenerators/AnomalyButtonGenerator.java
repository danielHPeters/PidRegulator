package com.pidregulator.componentgenerators;

import com.pidregulator.control.State;
import com.pidregulator.view.PaintSurface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Generater for Buttons which change some data in the list to create a
 * simulated anomaly. To generate a button use the generateButton method
 *
 * @author d.peters
 */
public class AnomalyButtonGenerator {

    /**
     * Reference to the drawing state
     */
    private final State state;

    /**
     * Reference to the Textfield that displays a message, when a button was
     * clicked
     */
    private final JTextField messageArea;

    /**
     * Default constructor. Initializes the references to the PaintSurface and
     * JTexField objects
     *
     * @param state
     * @param messageArea Reference to the JTextField object
     */
    public AnomalyButtonGenerator(State state, JTextField messageArea) {
        this.state = state;
        this.messageArea = messageArea;
    }

    /**
     * Generates an action for an anomalybutton. The text will be displayed on
     * the messageArea object, while the magnitude sets the scale of the anomaly
     *
     * @param anomalyText text to be displayed on the text area
     * @param magnitude the size of the anomaly
     * @return the generated action listener
     */
    private ActionListener createAnomalyAction(String buttonText, int magnitude) {

        ActionListener listener = (ActionEvent e) -> {
            this.messageArea.setText(buttonText);
            this.state.setAnomaly(magnitude);
        };

        return listener;
    }

    /**
     * Creates a button which creates an anomaly in the path of the plane
     *
     * @param buttonText the text to be displayed on the button
     * @param magnitude the size of the anomaly to be created by the button
     * @return the generated button
     */
    public JButton generateButton(String buttonText, int magnitude) {

        JButton button = new JButton(buttonText);

        button.setSize(10, 10);
        button.addActionListener(createAnomalyAction(buttonText, magnitude));

        return button;
    }
}
