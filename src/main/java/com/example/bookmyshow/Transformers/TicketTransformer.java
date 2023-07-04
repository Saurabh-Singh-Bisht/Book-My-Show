package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.ResponeDto.TicketResponseDto;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Models.Ticket;

public class TicketTransformer {
    public static TicketResponseDto generateTicket(Show show, Ticket ticket){

        TicketResponseDto ticketResponseDto = TicketResponseDto.builder()
                .movieName(show.getMovie().getMovieName())
                .location(show.getTheater().getLocation())
                .bookedSeats(ticket.getBookedSeats())
                .theaterName(show.getTheater().getName())
                .showDate(show.getDate())
                .showTime(show.getTime())
                .totalPrice(ticket.getTotalTicketPrice()).build();

        return ticketResponseDto;
    }
}
