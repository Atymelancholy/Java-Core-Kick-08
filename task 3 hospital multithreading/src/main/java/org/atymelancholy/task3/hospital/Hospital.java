package org.atymelancholy.task3.hospital;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atymelancholy.task3.hospital.model.HospitalConfig;
import org.atymelancholy.task3.hospital.resource.Pharmacy;
import org.atymelancholy.task3.hospital.resource.WardPool;

public final class Hospital {

    private static final Logger LOG = LogManager.getLogger(Hospital.class);
    private WardPool wardPool;
    private Pharmacy pharmacy;

    private Hospital() {
    }

    private static class Holder {
        static final Hospital INSTANCE = new Hospital();
    }

    public static Hospital getInstance() {
        return Holder.INSTANCE;
    }

    public void init(HospitalConfig config) {
        wardPool = new WardPool(config.getWards());
        pharmacy = new Pharmacy(config.getMedications());
        LOG.info("Hospital ready: {} wards, {} medications", config.getWards(), config.getMedications());
    }

    public WardPool wardPool() {
        return wardPool;
    }

    public Pharmacy pharmacy() {
        return pharmacy;
    }
}
