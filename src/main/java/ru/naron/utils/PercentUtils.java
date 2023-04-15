package ru.naron.utils;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class PercentUtils {
    
    public static final ThreadLocalRandom random = ThreadLocalRandom.current();
    
    public boolean randomPercent(double currentPercent){
        double randomPercent = randomDouble(0, 100);
        return randomPercent <= currentPercent;
    }

    public double randomDouble(double min, double max) {
        return min + random.nextDouble(max - min);
    }
}
