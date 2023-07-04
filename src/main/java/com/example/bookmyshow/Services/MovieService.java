package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.AddMovieDto;
import com.example.bookmyshow.Dtos.ResponeDto.ShowResponseDto;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.Ticket;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TicketRepository;
import com.example.bookmyshow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TicketRepository ticketRepository;
    public String addMovie(AddMovieDto addMovieDto) {
        Movie movie = MovieTransformer.convertDtoToEntity(addMovieDto);
        movieRepository.save(movie);
        return "Movie added successfully";
    }

    public Map<String, List<ShowResponseDto>> getMovieById(int movieId) {
        List<Show> showList = showRepository.findByMovieId(movieId);
        Map<String, List<ShowResponseDto>> movieShowList = MovieTransformer.convertShowListToMovieDto(showList);
        return movieShowList;
    }

    public String recommendMovie(Date date) {
        int movieId = showRepository.getMovieWithMostShows(date);
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.get().getMovieName();
    }

    public String getRevenue(int movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        List<Show> showList = movie.getShowList();
        long totalRevenue =0L;
        for (Show show: showList){
            totalRevenue += (long) ticketRepository.getRevenue(show.getId());
        }
        return "Total revenue till Date: " + totalRevenue;
    }

    public String getMovieWithMaxShows() {
        List<Movie> movieList = movieRepository.findAll();
        int maxShow =0;
        String movieName ="";
        for (Movie movie: movieList){
            if(movie.getShowList().size() > maxShow){
                maxShow = movie.getShowList().size();
                movieName = movie.getMovieName();
            }
        }
        return movieName;
    }
}
