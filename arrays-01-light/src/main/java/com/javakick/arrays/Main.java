package com.javakick.arrays;

import com.javakick.arrays.bootstrap.ApplicationComposition;
import com.javakick.arrays.bootstrap.KickArraysApplication;
import java.nio.file.Path;

public final class Main {

    public static void main(String[] args) {
        Main launcher = new Main();
        launcher.execute();
    }

    private void execute() {
        ApplicationComposition composition = new ApplicationComposition();
        KickArraysApplication application = composition.buildApplication();
        Path dataFile = Path.of("data", "arrays_input.txt");
        application.run(dataFile);
    }
}
