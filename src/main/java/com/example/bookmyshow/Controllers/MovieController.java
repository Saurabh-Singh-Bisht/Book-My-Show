package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.Dtos.RequestDto.AddMovieDto;
import com.example.bookmyshow.Dtos.ResponeDto.ShowResponseDto;
import com.example.bookmyshow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody AddMovieDto addMovieDto){
        return new ResponseEntity<>(movieService.addMovie(addMovieDto), HttpStatus.CREATED);
    }
    @GetMapping("/getMovie")
    public ResponseEntity<Map<String, List<ShowResponseDto>>> getMovieShows(@RequestParam int movieId){
        return new ResponseEntity<>(movieService.getMovieById(movieId), HttpStatus.FOUND);
    }
    @GetMapping("/get_recommended movie_name")
    public ResponseEntity<String> recommendMovie(@RequestParam Date date){
        return new ResponseEntity<>(movieService.recommendMovie(date), HttpStatus.FOUND);
    }
    @GetMapping("/totalRevenueTillNow")
    public ResponseEntity<String> getTotalRevenue(@RequestParam int movieId){
        return new ResponseEntity<String>(movieService.getRevenue(movieId), HttpStatus.FOUND);
    }
    @GetMapping("/movieWithMaximumNumberOfShows")
    public String movieWithMaxShows(){
        return movieService.getMovieWithMaxShows();
    }
}
