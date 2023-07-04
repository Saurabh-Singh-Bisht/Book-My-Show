package com.example.bookmyshow.Dtos.RequestDto;

import com.example.bookmyshow.Enums.Genre;
import com.example.bookmyshow.Enums.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.sql.Date;
@Data
public class AddMovieDto {
    private String movieName;
    private double duration;
    private double rating;
    private Date releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Language language;
}
