package e3.MFAStrategys;

import e3.MFAStrategy;

import java.util.Random;

public class threeNumbersMFA implements MFAStrategy {
    @Override
    public String generateCode() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(10)) + rand.nextInt(10) + rand.nextInt(10);
    }
}
