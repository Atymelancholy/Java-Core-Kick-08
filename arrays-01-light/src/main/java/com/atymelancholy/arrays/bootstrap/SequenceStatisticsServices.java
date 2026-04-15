package com.atymelancholy.arrays.bootstrap;

import com.atymelancholy.arrays.service.AverageService;
import com.atymelancholy.arrays.service.MinimumMaximumService;
import com.atymelancholy.arrays.service.SummationService;

public record SequenceStatisticsServices(
        MinimumMaximumService minimumMaximumService,
        SummationService summationService,
        AverageService averageService) {
}
