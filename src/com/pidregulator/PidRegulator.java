package com.pidregulator;

import com.pidregulator.control.FileLoader;
import com.pidregulator.control.PidData;
import com.pidregulator.control.Runner;
import com.pidregulator.control.State;
import com.pidregulator.interfaces.DataLoader;
import com.pidregulator.interfaces.ICanvas;
import com.pidregulator.view.swing.Canvas;
import com.pidregulator.view.swing.ControlBarGenerator;
import java.awt.Color;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class PidRegulator {
  private final State state;
  private final ScheduledThreadPoolExecutor executor;
  private final DataLoader loader;
  private final PidData data;
  private final Runner run;
  private final JFrame window;
  private final ICanvas surface;
  private final ControlBarGenerator controlBarGen;
  private final JPanel controlBar;

  /**
   * Default constructor. Creates the GUI.
   */
  public PidRegulator() {
    setLooks();
    // Create window
    window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(816, 880);
    window.setLayout(null);

    // Create File loader object
    loader = new FileLoader();

    // Init Data
    data = new PidData(loader);

    // Initialize the state of the program
    state = new State(data);

    // Painter object
    surface = new Canvas(data, state);

    // Runnable loop
    run = new Runner(surface, state);

    // Thread pool manager
    executor = new ScheduledThreadPoolExecutor(3);
    executor.scheduleAtFixedRate(run, 0L, 100L, TimeUnit.MILLISECONDS);

    // Control bar
    controlBarGen = new ControlBarGenerator(state);
    controlBar = controlBarGen.getNewControlBar();

    // Add components to window and display
    window.getContentPane().add(controlBar);
    window.getContentPane().add((JPanel) surface);
    window.setVisible(true);
  }

  /**
   * Sets look and feel of the program ui.
   */
  private void setLooks() {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (ClassNotFoundException | InstantiationException
        | IllegalAccessException | UnsupportedLookAndFeelException e) {
      System.out.println("Failed to set look and feel.");
    }
  }

  /**
   * Main process.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(PidRegulator::new);
  }
}
