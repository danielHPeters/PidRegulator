package com.pidregulator.control;

import com.pidregulator.model.DataLoader;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Loads data from a file.
 *
 * @author d.peters
 */
public class FileLoader implements DataLoader {

    /**
     * The file which holds the data
     */
    private String fileName;

    /**
     * Default construcotor. Sets the file.
     */
    public FileLoader() {
        this.fileName = "test.txt";
    }

    /**
     * Returns a list with the loaded data as doubles
     *
     * @return the completed list
     */
    @Override
    public List<Double> load() {

        String line;
        List<Double> soll = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(this.fileName));

            while ((line = br.readLine()) != null) {
                soll.add(Double.parseDouble(line.trim()));
            }

        } catch (IOException e) {
            System.out.println("Failed to open file: " + this.fileName);
        }

        return soll;
    }
}
