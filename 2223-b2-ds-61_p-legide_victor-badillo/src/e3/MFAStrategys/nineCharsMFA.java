package e3.MFAStrategys;

import e3.MFAStrategy;

import java.util.Random;

public class nineCharsMFA implements MFAStrategy {
    @Override
    public String generateCode() {
        Random rand = new Random();
        String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789abcdefghijklmnopqrstuvwxyz";

        return String.valueOf(c.charAt(rand.nextInt(c.length()))) + c.charAt(rand.nextInt(c.length())) + c.charAt(rand.nextInt(c.length()))
                + c.charAt(rand.nextInt(c.length())) + "-" + c.charAt(rand.nextInt(c.length())) + c.charAt(rand.nextInt(c.length()))
                + c.charAt(rand.nextInt(c.length())) + c.charAt(rand.nextInt(c.length()));
    }
}
