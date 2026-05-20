package org.atymelancholy.task3.hospital.config;

import org.atymelancholy.task3.hospital.model.HospitalConfig;
import org.atymelancholy.task3.hospital.model.PatientData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class HospitalConfigLoader {

    private HospitalConfigLoader() {
    }

    public static HospitalConfig load(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path).stream()
                .map(String::strip)
                .filter(line -> !line.isBlank() && !line.startsWith("#"))
                .toList();

        String[] header = lines.getFirst().split("\\s+");
        int wards = Integer.parseInt(header[0]);
        int medications = Integer.parseInt(header[1]);

        List<PatientData> patients = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("\\s+");
            patients.add(new PatientData(parts[0], Integer.parseInt(parts[1]), Long.parseLong(parts[2])));
        }
        return new HospitalConfig(wards, medications, patients);
    }
}
