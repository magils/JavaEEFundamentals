package com.mgil.labs.java.ee.ejb.client.executables;

import com.mgil.java.ee.ejb.remote.WelcomeUserRemote;
import com.mgil.labs.java.ee.ejb.client.WelcomeUserClient;

import javax.naming.NamingException;

public class WelcomeUserExecutable {

    public static void main(String args[]) throws NamingException {

        WelcomeUserClient welcomeUserClient = new WelcomeUserClient();

        WelcomeUserRemote welcomeUserRemote = welcomeUserClient.getWelcomeUserRemote();


        //Testing methods of the Welcome User Remote

       String response  =  welcomeUserRemote.welcomeNormal("Moises");
       String response2 = welcomeUserRemote.welcomeRespectful("Jhon");


       System.out.println(response);
       System.out.println(response2);

    }
}
