package com.mgil.java.labs.tickets.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "seat_type")
public class SeatType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private SeatPosition seatPosition;
    private BigDecimal price;
    private Integer quantity;
}
