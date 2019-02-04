package ch.peters.daniel.pidcontroller;

import ch.peters.daniel.pidcontroller.control.FileHandler;
import ch.peters.daniel.pidcontroller.control.PidData;
import ch.peters.daniel.pidcontroller.control.MainLoop;
import ch.peters.daniel.pidcontroller.control.State;
import ch.peters.daniel.pidcontroller.view.swing.CanvasSwing;
import ch.peters.daniel.pidcontroller.view.swing.ControlBarGenerator;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

/**
 * Main class.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class App {
  /**
   * Default constructor.
   * Creates the GUI.
   */
  public App() {
    var window = new JFrame();
    var loader = new FileHandler();
    var data = new PidData(loader);
    var state = new State(data);
    var surface = new CanvasSwing(data, state);
    var run = new MainLoop(surface, state);
    var executor = new ScheduledThreadPoolExecutor(3);
    var controlBarGen = new ControlBarGenerator(state);
    var controlBar = controlBarGen.getNewControlBar();

    executor.scheduleAtFixedRate(run, 0L, 100L, TimeUnit.MILLISECONDS);
    window.getContentPane().add(controlBar);
    window.getContentPane().add(surface);
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setSize(816, 880);
    window.setLayout(null);
    window.setVisible(true);
    setLooks();
    SwingUtilities.updateComponentTreeUI(window);
  }

  /**
   * Sets look and feel of the program ui.
   */
  private void setLooks() {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
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
    SwingUtilities.invokeLater(App::new);
  }
}
