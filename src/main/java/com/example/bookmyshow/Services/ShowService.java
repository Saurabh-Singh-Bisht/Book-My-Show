package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.AddShowDto;
import com.example.bookmyshow.Dtos.RequestDto.ShowSeatsDto;
import com.example.bookmyshow.Dtos.ResponeDto.ShowResponseDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Exceptions.MovieNotFound;
import com.example.bookmyshow.Exceptions.ShowNotFound;
import com.example.bookmyshow.Exceptions.TheaterNotFound;
import com.example.bookmyshow.Models.*;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ShowRepository showRepository;
    public String addShow(AddShowDto addShowDto) throws TheaterNotFound, MovieNotFound {
        Show show = ShowTransformer.convertDtoToEntity(addShowDto);

        //Set movie theater entity
        Optional<Movie> movieOptional = movieRepository.findById(addShowDto.getMovieId());
        if (movieOptional.isEmpty()){
            throw new MovieNotFound("Movie not found");
        }
        Optional<Theater> theaterOptional = theaterRepository.findById(addShowDto.getTheaterId());
        if(theaterOptional.isEmpty()){
            throw new TheaterNotFound("Theater not found");
        }
        Movie movie = movieOptional.get();
        Theater theater = theaterOptional.get();

        //Setting foreign keys
        show.setMovie(movie);
        show.setTheater(theater);

        show = showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show has been added with ID: " + show.getId();
    }

    public String associateShowSeats(ShowSeatsDto showSeatsDto) throws ShowNotFound {
        Optional<Show> showOptional = showRepository.findById(showSeatsDto.getShowId());
        if(showOptional.isEmpty()){
            throw new ShowNotFound("Show ID incorrect!!");
        }
        //Valid show now
        Show show = showOptional.get();
        //We need theater seats
        Theater theater = show.getTheater();
        List<TheaterSeats> theaterSeatsList = theater.getTheaterSeats();
        List<ShowSeats> showSeatsList = show.getShowSeatsList();
        for(TheaterSeats theaterSeats: theaterSeatsList){
            ShowSeats showSeats = new ShowSeats();
            showSeats.setSeatNo(theaterSeats.getSeatNo());
            showSeats.setSeatType(theaterSeats.getSeatType());
            if(showSeats.getSeatType().equals(SeatType.CLASSIC)){
                showSeats.setPrice(showSeatsDto.getPriceForClassicSeats());
            }
            else {
                showSeats.setPrice(showSeatsDto.getPriceForPremiumSeats());
            }
            //Foreign key mapping
            showSeats.setShow(show);
            showSeats.setAvailable(Boolean.TRUE);
            showSeats.setFoodAttached(Boolean.FALSE);

            showSeatsList.add(showSeats);
        }
        showRepository.save(show);
        return "Show seats added successfully";
    }

    public List<ShowResponseDto> getShowList(int theaterId, int movieId) {
        List<Show> showList = showRepository.getShowList(theaterId, movieId);
        List<ShowResponseDto> showResponseDtoList = ShowTransformer.convertShowListToShowListDto(showList);
        return showResponseDtoList;
    }
}
