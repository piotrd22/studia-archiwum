package lab01.zad03;

import java.util.HashMap;
import java.util.Map;

public class Zad03 {
    private enum Planet {
        ZIEMIA(1.0),
        MERKURY(0.2408467),
        WENUS(0.61519726),
        MARS(1.8808158),
        JOWISZ(11.862615),
        SATURN(29.447498),
        URAN(84.016846),
        NEPTUN(164.79132);

        private final double rotationRate;

        Planet(double rotationRate) {
            this.rotationRate = rotationRate;
        }

        public double getRotationRate() {
            return rotationRate;
        }
    }

    public static void main(String[] args) {
        double ageInSeconds = 1000000000.0;
        double earthSeconds = 31557600.0;

        for (Planet planet : Planet.values()) {
            double earthYears = ageInSeconds / earthSeconds;
            double planetYears = earthYears / planet.getRotationRate();
            System.out.println("Wiek na " + planet.name() + ": " + String.format("%.2f", planetYears) + " lat ziemskich");
        }
    }
}