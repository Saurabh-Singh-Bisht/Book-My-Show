package com.example.bookmyshow.Dtos.RequestDto;

import lombok.Data;

import java.time.LocalTime;
import java.sql.Date;
@Data
public class AddShowDto {
    private LocalTime showStartTime;
    private Date showDate;
    private int theaterId;
    private int movieId;
}
