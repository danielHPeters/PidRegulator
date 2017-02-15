package com.pidregulator;

import com.pidregulator.view.PaintSurface;
import com.pidregulator.control.FileLoader;
import com.pidregulator.control.Runner;
import com.pidregulator.control.PidData;
import com.pidregulator.model.DataLoader;
import com.pidregulator.componentgenerators.ControlBarGenerator;
import com.pidregulator.control.State;
import java.awt.Color;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author d.peters
 */
public class PidRegulator {

    /**
     * 
     */
    private final State state;
    
    /**
     * Threadpool manager
     */
    private final ScheduledThreadPoolExecutor executor;

    /**
     * Object loading the data
     */
    private final DataLoader loader;

    /**
     * Object holding all data lists
     */
    private final PidData data;

    /**
     * Runnable which updates the drawing process
     */
    private final Runner run;

    /**
     * The main window
     */
    private final JFrame window;

    /**
     * The painter object
     */
    private final PaintSurface surface;

    /**
     * Generator for controlbars
     */
    private final ControlBarGenerator controlBarGen;

    /**
     * Panel with controls
     */
    private final JPanel controlBar;

    public PidRegulator() {
        
        // Create window

        this.window = new JFrame();
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(816, 880);
        this.window.setLayout(null);
        
        // Create File loader object

        this.loader = new FileLoader();
        
        // Init Data

        this.data = new PidData(this.loader);
        
        // Initialize the state of the program
        
        this.state = new State(this.data);
        
        // Painter object

        this.surface = new PaintSurface(this.data, this.state);
        this.surface.setBackground(Color.blue);
        this.surface.setForeground(Color.white);
        this.surface.setBounds(0, 40, 800, 800);

        // Runnable loop
        
        this.run = new Runner(this.surface, this.state);
        
        // Threadpool manager
        
        this.executor = new ScheduledThreadPoolExecutor(3);
        this.executor.scheduleAtFixedRate(this.run, 0L, 100L, TimeUnit.MILLISECONDS);
        
        // Controlbar

        this.controlBarGen = new ControlBarGenerator(this.state);
        this.controlBar = this.controlBarGen.getNewControlBar();
        
        // Add components to window and display

        this.window.getContentPane().add(this.controlBar);
        this.window.getContentPane().add(this.surface);
        this.window.setVisible(true);

    }

    /**
     * Sets look and feel of the program ui.
     */
    public static void setLooks() {
        
        try {

            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        
    }

    /**
     * Main process.
     * 
     * @param args the command line arguments 
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            setLooks();
            PidRegulator program = new PidRegulator();
        });

    }
}
