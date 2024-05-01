package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;


/**
 * A file output strategy for storing cardio data in files on the local filesystem.
 * This class writes cardio data to text files, with each file containing data for a specific label.
 * It makes sure that patient data is stored in separate files and new data can be added/appended to existing files.
 */


//Class names are written in UpperCamelCase- changed fileOutputStrategy to FileOutputStrategy.
public class FileOutputStrategy implements OutputStrategy {
    // local variable names(static or otherwise) should be lowerCamelCase- changed BaseDirectory to baseDirectory.
     public String baseDirectory; //The base directory where files will be stored

    // local variable names(static or otherwise) should be lowerCamelCase- changed file_map to filemap.
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();// A map to store file paths corresponding to labels

    /**
     * Constructs a new FileOutputStrategy with the specified base directory.
     *
     * @param baseDirectory The base directory where files will be stored.
     */

    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory;
    }

    /**
     * the method outputs cardio data for a patient to a file.
     *
     * @param patientId The ID of the patient.
     * @param timestamp The timestamp of the cardio reading.
     * @param label     The label associated with the cardio reading.
     * @param data      The data of the cardio reading .
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        String FilePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(FilePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + FilePath + ": " + e.getMessage());
        }
    }
}