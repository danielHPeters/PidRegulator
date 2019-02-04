package ch.peters.daniel.pidcontroller.view.swing;

import ch.peters.daniel.pidcontroller.control.State;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Generator for buttons, that change the loop of a runnable in some way
 * (e.g. restarting the loop or changing the delay)
 *
 * @author Daniel Peters
 * @version 1.0
 */
class ControlButtonGenerator {
  private final State state;
  private final JTextField speedDisplay;

  /**
   * Default constructor. Initializes the reference to the runnable object and
   * the textfield displaying the speed.
   *
   * @param run          drawing state
   * @param speedDisplay textfield displaying execution speed
   */
  ControlButtonGenerator(State run, JTextField speedDisplay) {
    this.state = run;
    this.speedDisplay = speedDisplay;
  }

  /**
   * Returns an action listener the invokes the restart method of the runnable object.
   *
   * @return action listener for restart button
   */
  private ActionListener generateRestartAction() {
    return event -> this.state.reset();
  }

  /**
   * Creates an action listener that changes the delay of the loop in the
   * runnable and displays the new speed on the speedDisplay textfield.
   *
   * @param delayDiff difference to delay
   * @return action listener for button
   */
  private ActionListener generateDelayAction(int delayDiff) {
    return event -> {
      this.state.changeSpeed(delayDiff);
      this.speedDisplay.setText(Integer.toString((105 - this.state.getDelay())));
    };
  }

  /**
   * Generates a delay button with a delay action listener and returns it.
   *
   * @param text      the Text to be displayed on the button
   * @param delayDiff the amount by which the delay of the runnable is modified
   * @return the completed button
   */
  JButton generateDelayButton(String text, int delayDiff) {
    var button = new JButton(text);

    button.addActionListener(generateDelayAction(delayDiff));

    return button;
  }

  /**
   * Returns a button that restarts the whole loop of the runnable.
   *
   * @return the completed button
   */
  JButton generateRestartButton() {
    var button = new JButton("Restart");

    button.addActionListener(generateRestartAction());

    return button;
  }
}
