package data_management;

import com.alerts.Alert;
import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataStorageTest {

    @Test
    public void testAddRecord() {
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 75, "HeartRate", 1654770930000L);
        List<PatientRecord> records = storage.getRecords(1, 0, Long.MAX_VALUE);
        assertEquals(1, records.size());
        assertEquals(1, records.get(0).getPatientId());
    }

    @Test
    public void testGetRecords() {
        Patient patient = new Patient(1);
        patient.addRecord(75, "HeartRate", 1654770930000L);
        patient.addRecord(80, "HeartRate", 1654771230000L);
        long startTime = 1654770930000L;
        long endTime = 1654771230000L;
        List<PatientRecord> filteredRecords = patient.getRecords(startTime, endTime);
        assertEquals(2, filteredRecords.size());
    }

    @Test
    public void testEvaluateData() {
        DataStorage storage = new DataStorage();
        AlertGenerator alertGenerator = new AlertGenerator(storage);
        storage.addPatientData(1, 190, "BloodPressure", 1654770930000L);
        Patient patient = storage.getAllPatients().get(0);
        alertGenerator.evaluateData(patient);
        // Example assertion based on alert condition met
        // This will need to be captured by a mechanism inside triggerAlert, e.g., storing alerts in a list
    }
}
