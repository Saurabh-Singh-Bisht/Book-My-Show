package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.RequestDto.AddShowDto;
import com.example.bookmyshow.Dtos.ResponeDto.ShowResponseDto;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.ShowSeats;

import java.util.ArrayList;
import java.util.List;

public class ShowTransformer {
    public static Show convertDtoToEntity(AddShowDto addShowDto){
        Show show = Show.builder()
                .time(addShowDto.getShowStartTime())
                .date(addShowDto.getShowDate()).build();
        return show;
    }
    public static String convertListToString(List<String> list){
        String ans = "";
        for (String showSeats: list){
            ans = ans + showSeats + ", ";
        }
        return ans;
    }
    public static List<ShowResponseDto> convertShowListToShowListDto(List<Show> showList){
        List<ShowResponseDto> showResponseDtoList = new ArrayList<>();
        for (Show show: showList){
            ShowResponseDto showResponseDto = ShowResponseDto.builder()
                    .date(show.getDate())
                    .localTime(show.getTime()).build();
            showResponseDtoList.add(showResponseDto);
        }
        return showResponseDtoList;
    }
}
