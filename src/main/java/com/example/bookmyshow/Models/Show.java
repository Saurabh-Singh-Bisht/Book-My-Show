package com.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalTime time;
    private Date date;
    @ManyToOne
    @JoinColumn
    private Movie movie;
    @ManyToOne
    @JoinColumn
    private Theater theater;
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeats> showSeatsList = new ArrayList<>();
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();
}
