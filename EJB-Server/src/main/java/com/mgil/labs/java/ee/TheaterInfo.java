package com.mgil.labs.java.ee;

import com.mgil.java.ee.ejb.remote.TheaterInfoRemote;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Collection;


@Stateless
@Remote(TheaterInfoRemote.class)
public class TheaterInfo implements TheaterInfoRemote {

    @EJB
    private TheaterBox box;

    @Override
    public String printSeatList() {

         Collection<Seat> seats = box.getSeats();

         StringBuilder stringBuilder = new StringBuilder();


         for(Seat seat : seats){
             stringBuilder.append(seat.toString());
             stringBuilder.append(System.lineSeparator());
         }

        return stringBuilder.toString();
    }

    @Override
    public String printOnlyBookedSeats() {


        Collection<Seat> seats = box.getSeats();


        StringBuilder sb = new StringBuilder();

        for(Seat seat : seats){

            if(seat.isBooked()){
                sb.append(seat.toString());
            }

        }

        return sb.toString();
    }
}
