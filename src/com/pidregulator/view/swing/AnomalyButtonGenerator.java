package com.pidregulator.view.swing;

import com.pidregulator.control.State;

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
   * Default constructor. Initializes the references to the Canvas and
   * JTexField objects
   *
   * @param state       drawing state
   * @param messageArea Reference to the JTextField object
   */
  AnomalyButtonGenerator(State state, JTextField messageArea) {
    this.state = state;
    this.messageArea = messageArea;
  }

  /**
   * Generates an action for an anomaly button. The text will be displayed on
   * the messageArea object, while the magnitude sets the scale of the anomaly
   *
   * @param buttonText text to be displayed on the text area
   * @param magnitude  the size of the anomaly
   * @return the generated action listener
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
   * @param buttonText the text to be displayed on the button
   * @param magnitude  the size of the anomaly to be created by the button
   * @return the generated button
   */
  JButton generateButton(String buttonText, int magnitude) {
    JButton button = new JButton(buttonText);

    button.setSize(10, 10);
    button.addActionListener(createAnomalyAction(buttonText, magnitude));

    return button;
  }
}
