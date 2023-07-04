package com.example.bookmyshow.Dtos.RequestDto;

import lombok.Data;

@Data
public class TheaterSeatsEntryDto {
    private int noOfSeatIn1Row;
    private int noOfClassicSeats;
    private int noOfPremiumSeats;
    private String location;
}
