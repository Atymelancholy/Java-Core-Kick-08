package org.atymelancholy.task3.hospital.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Pharmacy {

    private static final Logger LOG = LogManager.getLogger(Pharmacy.class);
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition stockAvailable = lock.newCondition();
    private int stock;

    public Pharmacy(int initialStock) {
        this.stock = initialStock;
    }

    public void dispense(String patient, int amount) throws InterruptedException {
        lock.lock();
        try {
            while (stock < amount) {
                stockAvailable.await();
            }
            stock -= amount;
            stockAvailable.signalAll();
            LOG.info("Dispensed {} meds to {}, remaining {}", amount, patient, stock);
        } finally {
            lock.unlock();
        }
    }
}
