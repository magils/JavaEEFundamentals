package com.mgil.labs.java.ee;

import com.mgil.java.ee.ejb.commons.PrintableContentRemote;
import com.mgil.labs.java.ee.qualifiers.ObjectValue;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;


@Stateless
@Remote(PrintableContentRemote.class)
public class ProducerPrinter implements PrintableContentRemote {

/*    @Inject
    @NumberValue*/
    private int someNumber;

    @Inject
    @ObjectValue
    private Instance<String> instanceValue;

    @Override
    public String print() {
        return String.format("Number => %d ; String => %s",someNumber, instanceValue.get());
    }
}
