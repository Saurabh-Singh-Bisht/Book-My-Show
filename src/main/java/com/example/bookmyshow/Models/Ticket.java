package com.example.bookmyshow.Models;

import com.example.bookmyshow.Enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int totalTicketPrice;
    private String bookedSeats;
    @CreationTimestamp
    private Date bookedAt;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    @ManyToOne
    @JoinColumn
    private Show show;
    @ManyToOne
    @JoinColumn
    private User user;
}
