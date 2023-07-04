package com.example.bookmyshow.Dtos.ResponeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    private LocalTime showTime;
    private Date showDate;
    private String movieName;
    private String theaterName;
    private String bookedSeats;
    private String location;
    private Integer totalPrice;
}
