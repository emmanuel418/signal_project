package com.data_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads data from a specified file and stores it in the data storage.
 */
public class FileDataReader implements DataReader {
    private String filePath;

    /**
     * Constructs a new FileDataReader with the specified file path.
     *
     * @param filePath the path to the file from which data will be read
     */
    public FileDataReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from the specified file and stores it in the data storage.
     *
     * @param dataStorage the storage where data will be stored
     * @throws IOException if there is an error reading the data
     */
    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    int patientId = Integer.parseInt(values[0]);
                    long timestamp = Long.parseLong(values[1]);
                    String measurementType = values[2];
                    double measurementValue = Double.parseDouble(values[3]);

                    dataStorage.addPatientData(patientId, measurementValue, measurementType, timestamp);
                } else {
                    throw new IOException("Incorrect data format in file: " + line);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading data from file", e);
        }
    }
}
