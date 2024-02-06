package org.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        double[] data = {1.0, 2.0, 3.0, 4.0, 5.0};

        Map<String, Double> statistics = Compute.calculateStatistics(data);

        System.out.println("Średnia arytmetyczna: " + statistics.get("Mean"));
        System.out.println("Odchylenie standardowe: " + statistics.get("StandardDeviation"));
        System.out.println("Wariancja: " + statistics.get("Variance"));
    }
}