package ch.peters.daniel.pidcontroller.view.swing;

import ch.peters.daniel.pidcontroller.control.State;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates the top control bar of gui.
 *
 * @author Daniel Peters <daniel.peters.ch@gmail.com>
 * @version 1.0
 */
public class ControlBarGenerator {
  private final State state;

  /**
   * Default constructor.
   *
   * @param state Drawing state
   */
  public ControlBarGenerator(State state) {
    this.state = state;
  }

  /**
   * Generates a control bar.
   *
   * @return control bar
   */
  public JPanel getNewControlBar() {
    var controls = new JPanel();

    // Create and modify the anomaly text field
    var messageArea = new JTextField();

    messageArea.setColumns(10);
    messageArea.setEditable(false);

    // Create and modify the speed display
    var speedDisplay = new JTextField();

    speedDisplay.setColumns(10);
    speedDisplay.setEditable(false);
    speedDisplay.setText(Integer.toString((105 - this.state.getDelay())));

    // Get Button Generators
    var btnGenerator = new AnomalyButtonGenerator(
        this.state, messageArea);
    var controlsBtnGen = new ControlButtonGenerator(
        this.state, speedDisplay);

    controls.setBounds(0, 0, 800, 40);
    controls.setBackground(Color.yellow);

    // Generate and add buttons to controlBar
    var north = btnGenerator.generateButton("North", 5);
    var south = btnGenerator.generateButton("South", -5);
    var defaultAnomaly = btnGenerator.generateButton("Reset", 0);
    var restartButton = controlsBtnGen.generateRestartButton();
    var fasterButton = controlsBtnGen.generateDelayButton("Faster", -5);
    var slowerButton = controlsBtnGen.generateDelayButton("Slower", 5);

    controls.add(restartButton, BorderLayout.NORTH);
    controls.add(fasterButton, BorderLayout.NORTH);
    controls.add(slowerButton, BorderLayout.NORTH);
    controls.add(speedDisplay, BorderLayout.SOUTH);
    controls.add(north, BorderLayout.NORTH);
    controls.add(defaultAnomaly, BorderLayout.NORTH);
    controls.add(south, BorderLayout.NORTH);
    controls.add(messageArea, BorderLayout.SOUTH);

    return controls;
  }
}
