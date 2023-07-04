package com.example.bookmyshow.Models;

import com.example.bookmyshow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "show_seats")
@Data
public class ShowSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private String seatNo;
    private int price;
    private boolean isAvailable;
    private boolean isFoodAttached;
    @ManyToOne
    @JoinColumn
    private Show show;
}
