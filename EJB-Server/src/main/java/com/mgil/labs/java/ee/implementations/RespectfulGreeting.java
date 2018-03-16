package com.mgil.labs.java.ee.implementations;

import com.mgil.labs.java.ee.Greeting;

import javax.enterprise.inject.Default;

@Default
public class RespectfulGreeting implements Greeting {
    @Override
    public String greet(String name) {
        return String.format("Welcome Mr./Mrs. %s. It's a pleasure have you as a guest.",name);
    }
}
