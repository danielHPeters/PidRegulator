package ch.peters.daniel.pidcontroller.interfaces;

import java.util.List;

/**
 * Interface for dataLoading objects. This allows for easier implementation of
 * new data sources
 *
 * @author Daniel Peters
 * @version 1.0
 * @param <T> Data type
 */
public interface DataHandler<T> {
  List<T> get();
}
