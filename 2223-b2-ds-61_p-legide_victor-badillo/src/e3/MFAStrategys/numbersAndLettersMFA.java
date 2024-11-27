package e3.MFAStrategys;

import e3.MFAStrategy;

import java.util.Random;

public class numbersAndLettersMFA implements MFAStrategy {
    @Override
    public String generateCode() {
        Random rand = new Random();
        String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

        return String.valueOf(c.charAt(rand.nextInt(c.length()))) + c.charAt(rand.nextInt(c.length())) + c.charAt(rand.nextInt(c.length()))
                + c.charAt(rand.nextInt(c.length())) + c.charAt(rand.nextInt(c.length())) + c.charAt(rand.nextInt(c.length()));
    }
}
