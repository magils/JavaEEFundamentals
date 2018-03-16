package com.mgil.labs.java.ee.ejb.client;

import com.mgil.java.ee.ejb.commons.exceptions.NoSuchSeatException;
import com.mgil.java.ee.ejb.commons.exceptions.NotEnoughMoneyException;
import com.mgil.java.ee.ejb.commons.exceptions.SeatBookedException;
import com.mgil.java.ee.ejb.remote.TheaterBookerRemote;
import com.mgil.java.ee.ejb.remote.TheaterInfoRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class TicketAgentClient {

    private static final Logger logger = Logger.getLogger(TicketAgentClient.class.getName());

    private final Context context;
    private final List<Future<String>> lastBookings = new ArrayList<>();
    private TheaterInfoRemote theaterInfoRemote;
    private TheaterBookerRemote theaterBooker;


    public static void main(String args[]) {

        try {
            new TicketAgentClient().run();
        } catch (NamingException ex) {
            ex.printStackTrace();
            ;
        }
    }


    public TicketAgentClient() throws NamingException {

        final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.setProperty(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//        jndiProperties.put(Context.SECURITY_PRINCIPAL, "test");
//        jndiProperties.put(Context.SECURITY_CREDENTIALS, "test");

        /*
         * Note:
         * The http-remoting client assumes JNDI names in remote lookups are relative to java:jboss/exported namespace, a lookup of an absolute JNDI name will fail.
         * */


        this.context = new InitialContext(jndiProperties);
    }


    private void run() throws NamingException {

        System.out.println("Theatre booking system");
        System.out.println("=====================================");
        System.out.println("Commands: book, bookasync, list, booked, mail, money, quit");


        this.theaterInfoRemote = (TheaterInfoRemote) context.lookup("EJB-Server-1.0-SNAPSHOT/TheaterInfo!com.mgil.java.ee.ejb.remote.TheaterInfoRemote");
        this.theaterBooker = (TheaterBookerRemote) context.lookup("EJB-Server-1.0-SNAPSHOT/TheaterBooker!com.mgil.java.ee.ejb.remote.TheaterBookerRemote");


        while (true) {


            final String stringCommand = IOUtils.readLine("Command:");
            final Command command = Command.parseCommand(stringCommand);


            switch (command) {

                case BOOK:
                    handleBook();
                    break;

                case BOOKASYNC:
                    handleBookAsync();
                    break;

                case MAIL:
                    handleMail();
                    break;

                case BOOKED:
                    handleBookedList();
                    break;

                default:
                    logger.warning("Error: Unknown command");
                    break;

            }

        }


    }

    private void handleList() {
        logger.info(theaterInfoRemote.printSeatList());
    }

    private void handleBookedList() {
        logger.info(
                theaterInfoRemote.printOnlyBookedSeats()
        );
    }


    private void handleMail() {

        boolean displayed = false;

        List<Future<String>> notFinished = new ArrayList<>();

        for (Future<String> booking : lastBookings) {

            if (booking.isDone()) {


                try {

                    String bookedSeat = booking.get();
                    logger.info(String.format("Mail received:\n%s\n", bookedSeat));
                    displayed = true;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            } else {
                notFinished.add(booking);
            }
        }


        lastBookings.retainAll(notFinished);


        if(!displayed){
            logger.info("No mail received");
        }


    }


    private void handleBookAsync() {


        try {

            int seatId = IOUtils.readInt("Enter Seat ID:");
            lastBookings.add(theaterBooker.bookSeatAsync(seatId));

            logger.info("Booking issued. Verify your email!");

        } catch (NumberFormatException e) {
            logger.warning("Wrong seat ID Format");
            return;
        }


    }

    private void handleBook() {

        //Parsing the Seat ID
        int seatId;

        try {
            seatId = IOUtils.readInt("Enter Seat ID:");
        } catch (NumberFormatException e) {
            logger.warning("Wrong Seat ID format!");
            return;
        }


        //Booking a seat

        try {
            final String retVal = theaterBooker.bookSeat(seatId);
            System.out.println(retVal);
        } catch (NoSuchSeatException | NotEnoughMoneyException | SeatBookedException e) {
            e.printStackTrace();
        }

    }


}
