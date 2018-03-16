package com.mgil.java.ee.ejb.remote;

import com.mgil.java.ee.ejb.commons.exceptions.NoSuchSeatException;
import com.mgil.java.ee.ejb.commons.exceptions.NotEnoughMoneyException;
import com.mgil.java.ee.ejb.commons.exceptions.SeatBookedException;

import java.util.concurrent.Future;

public interface TheaterBookerRemote {

    int getAccountBalance();
    String bookSeat(int seatId) throws NoSuchSeatException, NotEnoughMoneyException, SeatBookedException;
    Future<String> bookSeatAsync(int seatId);


}
