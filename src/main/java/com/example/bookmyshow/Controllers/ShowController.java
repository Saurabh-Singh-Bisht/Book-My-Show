package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.Dtos.RequestDto.AddShowDto;
import com.example.bookmyshow.Dtos.RequestDto.ShowSeatsDto;
import com.example.bookmyshow.Dtos.ResponeDto.ShowResponseDto;
import com.example.bookmyshow.Exceptions.MovieNotFound;
import com.example.bookmyshow.Exceptions.ShowNotFound;
import com.example.bookmyshow.Exceptions.TheaterNotFound;
import com.example.bookmyshow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody AddShowDto addShowDto){
        try {
            String str = showService.addShow(addShowDto);
            return new ResponseEntity<>(str, HttpStatus.CREATED);
        }
        catch (MovieNotFound ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (TheaterNotFound ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/associate-seats")
    public ResponseEntity<String> associateSeats(@RequestBody ShowSeatsDto showSeatsDto){
        try {
            String ans = showService.associateShowSeats(showSeatsDto);
            return new ResponseEntity<>(ans, HttpStatus.CREATED);
        }
        catch (ShowNotFound ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/list-of-show-by-movie-&-theater")
    public ResponseEntity<List<ShowResponseDto>> getShowListByMovieAndTheater(@RequestParam int theaterId, @RequestParam int movieId){
        List<ShowResponseDto> showResponseDtoList = showService.getShowList(theaterId, movieId);
        return new ResponseEntity<>(showResponseDtoList, HttpStatus.FOUND);
    }
}
