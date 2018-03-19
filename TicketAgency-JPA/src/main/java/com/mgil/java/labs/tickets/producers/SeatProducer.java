package com.mgil.java.labs.tickets.producers;

import com.mgil.java.labs.tickets.dao.SeatDAO;
import com.mgil.java.labs.tickets.entities.Seat;
import com.mgil.java.labs.tickets.entities.SeatType;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class SeatProducer {

    @Inject
    private SeatDAO seatDAO;

    private List<Seat> seats;

    @PostConstruct
    public void retrieveAllSeats(){
        seats = seatDAO.findAll();
    }


    //TODO: Study this method
    public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SeatType member) {
        retrieveAllSeats();

    }


}
