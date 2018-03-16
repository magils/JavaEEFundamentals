package com.mgil.labs.java.ee;

import com.mgil.java.ee.ejb.remote.WelcomeUserRemote;
import com.mgil.labs.java.ee.qualifiers.Normal;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote(WelcomeUserRemote.class)
public class WelcomeUser implements WelcomeUserRemote {

    @Inject
    @Normal
    private Greeting normalGreeting;

    @Inject
    private Greeting respectfulGreeting;

    public String welcomeRespectful(String name){
        return respectfulGreeting.greet(name);
    }

    public String welcomeNormal(String name){
        return normalGreeting.greet(name);
    }

}
