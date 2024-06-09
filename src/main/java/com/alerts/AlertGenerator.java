package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

public class AlertGenerator {
    private DataStorage dataStorage;

    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void evaluateData(Patient patient) {
        List<PatientRecord> records = patient.getRecords(Long.MIN_VALUE, Long.MAX_VALUE);
        for (int i = 0; i < records.size(); i++) {
            PatientRecord record = records.get(i);

            // Blood Pressure Critical Threshold Alert
            if (record.getRecordType().equals("BloodPressure")) {
                double systolic = record.getMeasurementValue();
                if (systolic > 180 || systolic < 90) {
                    triggerAlert(new Alert(Integer.toString(record.getPatientId()), "Critical Blood Pressure", record.getTimestamp()));
                }
            }

            // Blood Oxygen Saturation Alert
            if (record.getRecordType().equals("BloodOxygen")) {
                double oxygenSaturation = record.getMeasurementValue();
                if (oxygenSaturation < 92) {
                    triggerAlert(new Alert(Integer.toString(record.getPatientId()), "Low Blood Oxygen Saturation", record.getTimestamp()));
                }
            }

            // Combined Alert: Hypotensive Hypoxemia Alert
            if (record.getRecordType().equals("BloodPressure") && record.getMeasurementValue() < 90) {
                for (PatientRecord oxygenRecord : records) {
                    if (oxygenRecord.getRecordType().equals("BloodOxygen") && oxygenRecord.getMeasurementValue() < 92) {
                        triggerAlert(new Alert(Integer.toString(record.getPatientId()), "Hypotensive Hypoxemia", record.getTimestamp()));
                    }
                }
            }

            // ECG Data Alerts
            if (record.getRecordType().equals("ECG")) {
                double average = calculateAverage(records, i, 5);
                if (Math.abs(record.getMeasurementValue() - average) > 1.5) { // example threshold
                    triggerAlert(new Alert(Integer.toString(record.getPatientId()), "Abnormal ECG Data", record.getTimestamp()));
                }
            }
        }
    }

    private double calculateAverage(List<PatientRecord> records, int currentIndex, int windowSize) {
        int start = Math.max(0, currentIndex - windowSize + 1);
        int end = currentIndex + 1;
        double sum = 0;
        int count = 0;
        for (int i = start; i < end; i++) {
            if (records.get(i).getRecordType().equals("ECG")) {
                sum += records.get(i).getMeasurementValue();
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }

    private void triggerAlert(Alert alert) {
        // This method can be extended to notify medical staff, log the alert, or perform other actions
        System.out.println("Alert Triggered: " + alert.getCondition() + " for Patient ID: " + alert.getPatientId() + " at " + alert.getTimestamp());
    }
}
