package org.atymelancholy.task3.hospital.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atymelancholy.task3.hospital.Hospital;
import org.atymelancholy.task3.hospital.state.PatientState;
import org.atymelancholy.task3.hospital.state.PatientState.InWard;
import org.atymelancholy.task3.hospital.state.PatientState.ReceivingMedication;
import org.atymelancholy.task3.hospital.state.PatientState.Treated;
import org.atymelancholy.task3.hospital.state.PatientState.Waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Patient implements Callable<String> {

    private static final Logger LOG = LogManager.getLogger(Patient.class);
    private final PatientData data;

    public Patient(PatientData data) {
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        Hospital hospital = Hospital.getInstance();
        String name = data.getName();

        PatientState state = new Waiting();
        String status = state.label();
        LOG.info("{} arrived [{}]", name, status);

        hospital.wardPool().occupy(name);
        state = new InWard();
        status = state.label();
        LOG.info("{} in ward [{}]", name, status);

        TimeUnit.MILLISECONDS.sleep(data.getTreatmentMs());

        state = new ReceivingMedication();
        status = state.label();
        LOG.info("{} requesting {} meds [{}]", name, data.getMedicationsRequired(), status);
        hospital.pharmacy().dispense(name, data.getMedicationsRequired());

        state = new Treated();
        status = state.label();
        LOG.info("{} discharged [{}]", name, status);
        hospital.wardPool().release(name);
        return name;
    }
}
