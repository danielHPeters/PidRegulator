package ch.peters.daniel.pidcontroller.view.swing;

import ch.peters.daniel.pidcontroller.control.State;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Generator for Buttons which change some data in the list to create a
 * simulated anomaly. To generate a button use the generateButton method.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class AnomalyButtonGenerator {
  private final State state;
  private final JTextField messageArea;

  /**
   * Default constructor. Initializes the references to the CanvasSwing and
   * JTextField objects.
   *
   * @param state       Drawing state
   * @param messageArea Reference to the JTextField object
   */
  AnomalyButtonGenerator(State state, JTextField messageArea) {
    this.state = state;
    this.messageArea = messageArea;
  }

  /**
   * Generates an action for an anomaly button. The text will be displayed on
   * the messageArea object, while the magnitude sets the scale of the anomaly.
   *
   * @param buttonText Text to be displayed on the text area
   * @param magnitude  The size of the anomaly
   * @return The generated action listener
   */
  private ActionListener createAnomalyAction(String buttonText, int magnitude) {
    return event -> {
      messageArea.setText(buttonText);
      state.setAnomaly(magnitude);
    };
  }

  /**
   * Creates a button which creates an anomaly in the path of the plane.
   *
   * @param buttonText The text to be displayed on the button
   * @param magnitude  The size of the anomaly to be created by the button
   * @return The generated button
   */
  JButton generateButton(String buttonText, int magnitude) {
    var button = new JButton(buttonText);

    button.setSize(10, 10);
    button.addActionListener(createAnomalyAction(buttonText, magnitude));

    return button;
  }
}
