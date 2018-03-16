package com.mgil.labs.java.ee.ejb.client;

import com.mgil.java.ee.ejb.remote.WelcomeUserRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class WelcomeUserClient {

    private Context context;
    private WelcomeUserRemote welcomeUserRemote;

    public WelcomeUserClient() throws NamingException{


        Properties remoteConnectionProperties = new Properties();

        remoteConnectionProperties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
        remoteConnectionProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
        remoteConnectionProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");


        context = new InitialContext(remoteConnectionProperties);

        welcomeUserRemote = getWelcomeUserInstance();

    }


    private WelcomeUserRemote getWelcomeUserInstance() throws NamingException {
        WelcomeUserRemote wu = (WelcomeUserRemote) context.lookup("/EJB-Server-1.0-SNAPSHOT/WelcomeUser!com.mgil.java.ee.ejb.remote.WelcomeUserRemote");
        return  wu;
    }

    public WelcomeUserRemote getWelcomeUserRemote() {
        return welcomeUserRemote;
    }
}
