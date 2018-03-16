package com.mgil.labs.java.ee;

public class Seat {

    private final int id;
    private final String name;
    private final int price;
    private boolean booked;


    public Seat(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public Seat(int id, String name, int price, boolean booked) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.booked = booked;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }


    public void setBooked(boolean booked){
        this.booked = booked;
    }


    public Seat getBookedSeat(){
        return new Seat(getId(),getName(), getPrice(),true);
    }


    @Override
    public String toString() {
        return "Seat{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", booked=" + booked +
            '}';
    }
}
