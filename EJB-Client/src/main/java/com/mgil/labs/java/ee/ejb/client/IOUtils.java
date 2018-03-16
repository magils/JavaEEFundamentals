package com.mgil.labs.java.ee.ejb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtils {

     private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

     public static String readLine(String string) {


         System.out.print(string);

         try {
             return bufferedReader.readLine();
         } catch (IOException e) {
             e.printStackTrace();
         }

         return null;

     }



     public static int readInt(String string) {
         System.out.print(string);


         final String text;
         try {
             text = bufferedReader.readLine();
             return Integer.parseInt(text);
         } catch (IOException e) {
             e.printStackTrace();
         }


         return 0;
     }


}
