package com.pidregulator.componentgenerators;

import com.pidregulator.control.Runner;
import com.pidregulator.control.State;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Generator for buttons, that change the loop of a runnable in some way (e.g.
 * restarting the loop or changing the delay)
 *
 * @author d.peters
 */
public class ControlButtonGenerator {

    /**
     * Reference to the runnable object
     */
    private final State state;

    /**
     * Testfield displaying the current speed
     */
    private final JTextField speedDisplay;

    /**
     * Default constructor. Initializes the reference to the runnable object and
     * the textfield displaying the speed
     *
     * @param run
     * @param speedDisplay
     */
    public ControlButtonGenerator(State run, JTextField speedDisplay) {
        this.state = run;
        this.speedDisplay = speedDisplay;
    }

    /**
     * Returns an actionlistener the invokes the restart method of the runnable
     * object
     *
     * @return
     */
    private ActionListener generateRestartAction() {

        ActionListener listener = (ActionEvent e) -> {
            this.state.reset();
        };

        return listener;
    }

    /**
     * Creates an action listener that changes the delay of the loop in the
     * runnable and displays the new speed on the speedDisplay textfield
     *
     * @param delayDiff
     * @return
     */
    private ActionListener generateDelayAction(int delayDiff) {

        ActionListener listener = (ActionEvent e) -> {
            this.state.changeSpeed(delayDiff);
            this.speedDisplay.setText(Integer.toString((105 - this.state.getDelay())));
        };

        return listener;
    }

    /**
     * Generates a delay button with a delay action listener and returns it
     *
     * @param text the Ttxt to be displayed on the button
     * @param delayDiff the amount by which the delay of the runnable is
     * modified
     * @return the completed button
     */
    public JButton generateDelayButton(String text, int delayDiff) {

        JButton button = new JButton(text);
        ActionListener listener = generateDelayAction(delayDiff);

        button.addActionListener(listener);

        return button;
    }

    /**
     * Returns a button that restarts the whole loop of the runnable
     *
     * @return the completed button
     */
    public JButton generateRestartButton() {

        JButton button = new JButton("Restart");
        ActionListener listener = generateRestartAction();

        button.addActionListener(listener);

        return button;
    }

}
