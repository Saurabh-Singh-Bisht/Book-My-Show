package com.example.bookmyshow.Dtos.ResponeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.sql.Date;
import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowResponseDto {
    private LocalTime localTime;
    private Date date;
}
