package com.cardio_generator;

import com.data_management.DataStorage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("DataStorage")) {
            try {
                DataStorage.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            HealthDataSimulator.main(new String[]{});
        }
    }
}
