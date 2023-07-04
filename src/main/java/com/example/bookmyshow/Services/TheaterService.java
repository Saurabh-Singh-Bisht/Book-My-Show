package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.TheaterEntryDto;
import com.example.bookmyshow.Dtos.RequestDto.TheaterSeatsEntryDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Models.TheaterSeats;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    public String addTheater(TheaterEntryDto theaterEntryDto) {
        Theater theater = TheaterTransformer.convertDtoToEntity(theaterEntryDto);
        theaterRepository.save(theater);
        return "Theater added successfully";
    }
    public String addTheaterSeats(TheaterSeatsEntryDto theaterSeatsEntryDto){
        int columns = theaterSeatsEntryDto.getNoOfSeatIn1Row();
        int noOfClassicSeats = theaterSeatsEntryDto.getNoOfClassicSeats();
        int noOfPremiumSeats = theaterSeatsEntryDto.getNoOfPremiumSeats();

        Theater theater = theaterRepository.findByLocation(theaterSeatsEntryDto.getLocation());
        List<TheaterSeats> theaterSeatsList = theater.getTheaterSeats();

        int row =1;
        char ch = 'A';
        for(int count =1;count<=noOfClassicSeats;count++){
            String seat = row+"";
            seat = seat + ch;
            if ((ch-'A'+1) == columns){
                ch ='A'-1;
                row++;
            }
            ch++;

            TheaterSeats theaterSeats = new TheaterSeats();
            theaterSeats.setTheater(theater);
            theaterSeats.setSeatType(SeatType.CLASSIC);
            theaterSeats.setSeatNo(seat);
            theaterSeatsList.add(theaterSeats);
        }
        ch = 'A';
        for(int count =1;count<=noOfPremiumSeats;count++){
            String seat = row+"";
            seat = seat + ch;
            if ((ch-'A'+1) == columns){
                ch ='A' -1;
                row++;
            }
            ch++;

            TheaterSeats theaterSeats = new TheaterSeats();
            theaterSeats.setTheater(theater);
            theaterSeats.setSeatType(SeatType.PREMIUM);
            theaterSeats.setSeatNo(seat);
            theaterSeatsList.add(theaterSeats);
        }
        theaterRepository.save(theater);
        return "Theater seats has been successfully added";
    }
}
