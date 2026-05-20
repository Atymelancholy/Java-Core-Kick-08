package com.atymelancholy.arrays.warehouse;

import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.service.AverageService;
import com.atymelancholy.arrays.service.MinimumMaximumService;
import com.atymelancholy.arrays.service.SummationService;

public final class SequenceStatisticsCalculator {

    private final MinimumMaximumService minimumMaximumService;
    private final SummationService summationService;
    private final AverageService averageService;

    public SequenceStatisticsCalculator(
            MinimumMaximumService minimumMaximumService,
            SummationService summationService,
            AverageService averageService) {
        this.minimumMaximumService = minimumMaximumService;
        this.summationService = summationService;
        this.averageService = averageService;
    }

    public SequenceStatistics calculate(IntegerSequence sequence) {
        int min = minimumMaximumService.findMinimum(sequence).orElse(0);
        int max = minimumMaximumService.findMaximum(sequence).orElse(0);
        long sum = summationService.sum(sequence).orElse(0L);
        double average = averageService.average(sequence).orElse(0.0);
        return new SequenceStatistics(min, max, sum, average, sequence.getLength());
    }
}
