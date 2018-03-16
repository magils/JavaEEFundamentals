package com.mgil.labs.java.ee.ejb.client;

public enum Command{
    BOOK,BOOKASYNC,MAIL,LIST,BOOKED, MONEY, QUIT, INVALID;



    public static Command parseCommand(String stringCommand) {

        try {
            return valueOf(stringCommand.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }

    }



}
