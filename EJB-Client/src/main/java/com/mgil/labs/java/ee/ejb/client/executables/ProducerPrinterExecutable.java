package com.mgil.labs.java.ee.ejb.client.executables;


import com.mgil.java.ee.ejb.commons.PrintableContentRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ProducerPrinterExecutable {

    public static void main(String args[]) throws NamingException {


        Properties remoteConnectionProperties = new Properties();

        remoteConnectionProperties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
        remoteConnectionProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
        remoteConnectionProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");

        Context context = new InitialContext(remoteConnectionProperties);

        PrintableContentRemote cdiProducerPrinter = (PrintableContentRemote) context.lookup("/EJB-Server-1.0-SNAPSHOT/ProducerPrinter!com.mgil.java.ee.ejb.commons.PrintableContentRemote");

        System.out.println(cdiProducerPrinter.print());

    }


}
