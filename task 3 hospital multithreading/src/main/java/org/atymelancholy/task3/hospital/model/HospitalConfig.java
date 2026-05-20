package org.atymelancholy.task3.hospital.model;

import java.util.List;

public class HospitalConfig {

    private final int wards;
    private final int medications;
    private final List<PatientData> patients;

    public HospitalConfig(int wards, int medications, List<PatientData> patients) {
        this.wards = wards;
        this.medications = medications;
        this.patients = patients;
    }

    public int getWards() {
        return wards;
    }

    public int getMedications() {
        return medications;
    }

    public List<PatientData> getPatients() {
        return patients;
    }
}
