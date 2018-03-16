package com.mgil.labs.java.ee.injection;

import java.util.Random;

public class IsbnGenerator implements NumberGenerator {

    private Random random;

    public IsbnGenerator(){
        random = new Random();
    }

    @Override
    public String generateNumber() {
        return "13-" +  random.nextInt(Integer.MAX_VALUE);
    }
}
