package com.mgil.labs.java.ee.cdi;

import com.mgil.labs.java.ee.qualifiers.NumberValue;
import com.mgil.labs.java.ee.qualifiers.ObjectValue;

import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import java.util.Random;

public class CDIObjectProducers {

    private final int DEFAULT_CODE = 100;

    @Produces
    @NumberValue
     public int getDefaultCode() {
        System.out.println("Producing an Integer value");
        return DEFAULT_CODE;
    }


    @Produces
    @ObjectValue
    public String getDefaultStringValue() {
        return "Hello World";
    }

}
