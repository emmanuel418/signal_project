package com.cardio_generator.outputs;

/**
 * This interface sets the rules for how the output strategies in the cardio generator system work .
 * Output strategies are responsible for outputting data related to a patient's cardio readings.
 */
public interface OutputStrategy {

    /**
     * Outputs cardio data for a patient.
     *
     * @param patientId The ID of the patient.
     * @param timestamp The timestamp of the cardio reading.
     * @param label     The label associated with the cardio reading.
     * @param data      The data of the cardio reading.
     */
    void output(int patientId, long timestamp, String label, String data);
}
