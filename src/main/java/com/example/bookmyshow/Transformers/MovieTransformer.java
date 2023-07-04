package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.RequestDto.AddMovieDto;
import com.example.bookmyshow.Dtos.ResponeDto.ShowResponseDto;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Models.Show;

import java.util.*;

public class MovieTransformer {
    public static Movie convertDtoToEntity(AddMovieDto addMovieDto){
        Movie movie = Movie.builder()
                .movieName(addMovieDto.getMovieName())
                .duration(addMovieDto.getDuration())
                .rating(addMovieDto.getRating())
                .releaseDate(addMovieDto.getReleaseDate())
                .genre(addMovieDto.getGenre())
                .language(addMovieDto.getLanguage())
                .build();
        return movie;
    }
    public static Map<String, List<ShowResponseDto>> convertShowListToMovieDto(List<Show> showList){
        Map<String, List<ShowResponseDto>> map = new HashMap<>();
        for (Show show: showList){
            ShowResponseDto showResponseDto = new ShowResponseDto();
            showResponseDto.setDate(show.getDate());
            showResponseDto.setLocalTime(show.getTime());

            List<ShowResponseDto> showResponseDtoList = new ArrayList<>();

            if (map.containsKey(show.getTheater().getName())){
                showResponseDtoList = map.get(show.getTheater().getName());
            }
            showResponseDtoList.add(showResponseDto);
            map.put(show.getTheater().getName(), showResponseDtoList);
        }
        return map;
    }
}
