package com.mgil.labs.java.ee;


import com.mgil.java.ee.ejb.commons.exceptions.NoSuchSeatException;
import com.mgil.java.ee.ejb.commons.exceptions.NotEnoughMoneyException;
import com.mgil.java.ee.ejb.commons.exceptions.SeatBookedException;
import com.mgil.java.ee.ejb.remote.TheaterBookerRemote;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;



@Stateful
@Remote(TheaterBookerRemote.class)
@AccessTimeout(value = 5 , unit = TimeUnit.MINUTES)
public class TheaterBooker implements TheaterBookerRemote {

    @EJB
    private TheaterBox theaterBox;
    private int money;

    private static Logger logger = Logger.getLogger(TheaterBooker.class);

    @PostConstruct
    private void init(){
        //Set up customer  money
        money = 100;
    }


    @Override
    public int getAccountBalance() {
        return 0;
    }

    @Override
    public String bookSeat(int seatId) throws NoSuchSeatException, NotEnoughMoneyException, SeatBookedException {

        final int seatPrice = theaterBox.getSeatPrice(seatId);


        if(seatPrice > money) {
            throw new NotEnoughMoneyException("You does not have enough money for this transaction");
        }


        theaterBox.buyTicket(seatId);

        money -= seatPrice;

        logger.infov("Seat {0} booked!",seatId);

        return String.format("Seat with ID %d booked",seatId);
    }

    @Asynchronous
    @Override
    public Future<String> bookSeatAsync(int seatId) {

        try {
            Thread.sleep(1000);
            bookSeat(seatId);
            return new AsyncResult<>(String.format("Booked seat %d. Money left $%d\n",seatId,money));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchSeatException e) {
            e.printStackTrace();
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (SeatBookedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
