package org.atymelancholy.task3.hospital.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class WardPool {

    private static final Logger LOG = LogManager.getLogger(WardPool.class);
    private final Semaphore wards;

    public WardPool(int count) {
        this.wards = new Semaphore(count);
    }

    public void occupy(String patient) throws InterruptedException {
        wards.acquire();
        LOG.info("Ward assigned to {}", patient);
    }

    public void release(String patient) {
        wards.release();
        LOG.info("Ward freed by {}", patient);
    }
}
