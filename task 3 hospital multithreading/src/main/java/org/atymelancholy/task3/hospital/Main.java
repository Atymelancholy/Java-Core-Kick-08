package org.atymelancholy.task3.hospital;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atymelancholy.task3.hospital.config.HospitalConfigLoader;
import org.atymelancholy.task3.hospital.model.HospitalConfig;
import org.atymelancholy.task3.hospital.model.Patient;
import org.atymelancholy.task3.hospital.model.PatientData;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        Path configPath = args.length > 0
                ? Path.of(args[0])
                : Path.of(Objects.requireNonNull(
                        Main.class.getResource("/hospital.txt"), "hospital.txt not found").toURI());

        HospitalConfig config = HospitalConfigLoader.load(configPath);
        Hospital.getInstance().init(config);

        try (ExecutorService executor = Executors.newFixedThreadPool(config.getPatients().size())) {
            List<Future<String>> futures = new ArrayList<>();

            for (PatientData patientData : config.getPatients()) {
                futures.add(executor.submit(new Patient(patientData)));
            }

            for (Future<String> future : futures) {
                LOG.info("Treatment finished for {}", future.get());
            }
        }

        LOG.info("All patients treated");
    }
}
