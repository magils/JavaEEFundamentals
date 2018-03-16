package com.mgil.labs.java.ee;

import com.mgil.java.ee.ejb.commons.exceptions.NoSuchSeatException;
import com.mgil.java.ee.ejb.commons.exceptions.SeatBookedException;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Singleton
@Startup
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
public class TheaterBox {

    private static Logger logger = Logger.getLogger(TheaterBox.class);

    private Map<Integer,Seat> seats;


    @PostConstruct
    public void setupTheather() {


        seats = new HashMap<>();

        int id = 0;

        for(int i = 0 ; i < 5 ; i ++ ){

            addSeat(new Seat(++id, "Stalls",40));
            addSeat(new Seat(++id, "Circle",20));
            addSeat(new Seat(++id, "Balcony",30));

        }

        logger.info("Theater is setup!");

    }



    @Lock(LockType.READ)
    public Collection<Seat> getSeats(){
        return Collections.unmodifiableCollection(seats.values());
    }


    @Lock(LockType.READ)
    public int getSeatPrice(int seatId) throws NoSuchSeatException {
        return this.getSeat(seatId).getPrice();

    }


    @Lock(LockType.WRITE)
    public void buyTicket(int seatId) throws SeatBookedException, NoSuchSeatException{

        final Seat seat = getSeat(seatId);

        if(seat.isBooked()){
            throw new SeatBookedException(String.format("The seat with ID %d is booked",seat.getId()));
        }

        addSeat(seat.getBookedSeat());

    }


    //Internals and utilties methods
    private void addSeat(Seat seat) {
        seats.put(seat.getId(),seat);
    }

    @Lock(LockType.READ)
    private Seat getSeat(int seatId) throws NoSuchSeatException {

        final Seat seat = seats.get(seatId);

        if(seat == null){
            throw new NoSuchSeatException(String.format("The seat %d does not exists",seatId));
        }

        return seat;

    }

}
