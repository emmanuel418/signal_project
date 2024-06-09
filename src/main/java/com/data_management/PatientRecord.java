package com.data_management;

/**
 * Represents a single record of patient data at a specific point in time.
 * This class stores all necessary details for a single observation or measurement
 * taken from a patient, including the type of record (such as ECG, blood pressure),
 * the measurement value, and the exact timestamp when the measurement was taken.
 */
public class PatientRecord {
    private int patientId;
    private long timestamp;
    private double measurementValue;
    private String recordType; // Example: ECG, blood pressure, etc.

    /**
     * Constructs a new patient record with specified details.
     *
     * @param patientId        the unique identifier for the patient
     * @param timestamp        the time at which the measurement was recorded, in milliseconds since epoch
     * @param measurementValue the numerical value of the recorded measurement
     * @param recordType       the type of measurement (e.g., "ECG", "Blood Pressure")
     */
    public PatientRecord(int patientId, long timestamp, double measurementValue, String recordType) {
        this.patientId = patientId;
        this.timestamp = timestamp;
        this.measurementValue = measurementValue;
        this.recordType = recordType;
    }

    /**
     * Returns the patient ID associated with this record.
     *
     * @return the patient ID
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Returns the timestamp when this record was taken.
     *
     * @return the timestamp in milliseconds since epoch
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the measurement value of this record.
     *
     * @return the measurement value
     */
    public double getMeasurementValue() {
        return measurementValue;
    }

    /**
     * Returns the type of record (e.g., "ECG", "Blood Pressure").
     *
     * @return the record type
     */
    public String getRecordType() {
        return recordType;
    }
}
