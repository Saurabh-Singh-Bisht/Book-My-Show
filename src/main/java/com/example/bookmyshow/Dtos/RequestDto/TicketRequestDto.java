package com.example.bookmyshow.Dtos.RequestDto;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequestDto {
    private int userId;
    private int showId;
    private List<String> requestedSeats;
}
