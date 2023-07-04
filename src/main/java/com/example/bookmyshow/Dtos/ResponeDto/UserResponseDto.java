package com.example.bookmyshow.Dtos.ResponeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;
    private int age;
    private String mobileNo;

    private String statusCode;
    private String statusMessage;
}
