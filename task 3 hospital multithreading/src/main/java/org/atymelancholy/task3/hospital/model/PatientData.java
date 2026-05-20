package org.atymelancholy.task3.hospital.model;

public class PatientData {

    private final String name;
    private final int medicationsRequired;
    private final long treatmentMs;

    public PatientData(String name, int medicationsRequired, long treatmentMs) {
        this.name = name;
        this.medicationsRequired = medicationsRequired;
        this.treatmentMs = treatmentMs;
    }

    public String getName() {
        return name;
    }

    public int getMedicationsRequired() {
        return medicationsRequired;
    }

    public long getTreatmentMs() {
        return treatmentMs;
    }
}
