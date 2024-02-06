package org.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.HashMap;
import java.util.Map;

public class Compute {
    public static Map<String, Double> calculateStatistics(double[] data) {
        DescriptiveStatistics stats = new DescriptiveStatistics();

        for (double value : data) {
            stats.addValue(value);
        }

        Map<String, Double> statistics = new HashMap<>();
        statistics.put("Mean", stats.getMean());
        statistics.put("StandardDeviation", stats.getStandardDeviation());
        statistics.put("Variance", stats.getVariance());

        return statistics;
    }
}
