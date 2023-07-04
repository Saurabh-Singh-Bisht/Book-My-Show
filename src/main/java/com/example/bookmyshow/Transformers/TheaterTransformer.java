package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.RequestDto.TheaterEntryDto;
import com.example.bookmyshow.Models.Theater;

public class TheaterTransformer {
    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto){
        Theater theater = Theater.builder()
                .name(theaterEntryDto.getName())
                .location(theaterEntryDto.getLocation())
                .build();
        return theater;
    }
}
