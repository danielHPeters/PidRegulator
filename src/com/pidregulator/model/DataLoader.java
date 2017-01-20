package com.pidregulator.model;

import java.util.List;

/**
 * Interface for dataLoading objects. This allows for easier implementation of
 * new data sources
 *
 * @author d.peters
 */
public interface DataLoader {

    /**
     * Loads data and returns it in list form
     *
     * @return the data in a list form
     */
    public List<Double> load();

}
