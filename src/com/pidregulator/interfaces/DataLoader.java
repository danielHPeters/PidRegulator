package com.pidregulator.interfaces;

import java.util.List;

/**
 * Interface for dataLoading objects. This allows for easier implementation of
 * new data sources
 *
 * @author Daniel Peters
 * @version 1.0
 */
public interface DataLoader {
  List<Double> load();
}
