/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidregulator.control;

/**
 *
 * @author d.peters
 */
public class State {
    
    /**
     * anomaly (deviation from the intended course of the plane) can be changed
     * by the anomaly buttons. TODO: move to somewhere where it makes sense,
     * because this class should only handle painting of objects
     */
    private double anomaly;

    /**
     * A state counter limiting the amount of lines to be drawn from the data
     * (makes the line to be drawn in realtime instead of instantly on program
     * start).
     */
    private int state;
    
    /**
     * Flag for checking if the program has drawn all lines
     */
    private boolean running;
    
    /**
     * Delay of the painting thread Prevents the lines to be instantly drawn on
     * program start.
     *
     */
    private int delay;
    
    private final PidData data;
    
    /**
     * 
     * @param data
     */
    public State(PidData data){
        this.anomaly = 0;
        this.state = 0;
        this.running = true;
        this.delay = 30;
        this.data = data;
    }
    
    /**
     * Gets the current state of the drawing process.
     *
     * @return
     */
    public int getState() {
        return this.state;
    }

    /**
     * Increases the drawing state by one.
     */
    public void incrementState() {
        this.state++;
    }

    /**
     * 
     * @return 
     */
    public double getAnomaly() {
        return anomaly;
    }
    

    /**
     * Setter for the anomaly
     *
     * @param anomaly the new anomaly
     */
    public void setAnomaly(double anomaly) {
        this.anomaly = anomaly;
    }

    /**
     * 
     * @return 
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * 
     * @param running 
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    /**
     * Resets the drawing state and starts drawing from starting point
     */
    public void reset() {
        this.state = 0;
        this.running = true;
    }
    
    /**
     * Getter for the delay of the thread
     *
     * @return the current delay
     */
    public int getDelay() {
        return this.delay;
    }
    
    /**
     * Change the delay of the loop by delayDiff Sets limits to the delay
     * (longest: 100, shortest: 5)
     *
     * @param delayDiff ammount by which the delay will be modified
     */
    public void changeSpeed(int delayDiff) {

        if (this.delay + delayDiff > 0 && this.delay + delayDiff <= 100) {

            this.delay += delayDiff;

        }

    }

    /**
     * 
     * @return 
     */
    public PidData getData() {
        return data;
    }
    
    
    
}
