package com.mgil.labs.java.ee.implementations;

import com.mgil.labs.java.ee.Greeting;
import com.mgil.labs.java.ee.qualifiers.Normal;

@Normal
public class NormalGreeting implements Greeting {
    @Override
    public String greet(String name) {
        return String.format("Welcome %s!", name);
    }
}
