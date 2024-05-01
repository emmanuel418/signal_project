package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * this interface represents a cardio data generator for a specific patient.
 */
public interface PatientDataGenerator {
    /**
     *this method generates data for a specific patient and outputs it using the provided output strategy.
     *
     * @param patientId       The ID of the patient.
     * @param outputStrategy  The output strategy used to output the data that has been genrated.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
