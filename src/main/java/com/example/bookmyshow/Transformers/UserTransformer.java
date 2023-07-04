package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.RequestDto.AddUserDto;
import com.example.bookmyshow.Dtos.ResponeDto.UserResponseDto;
import com.example.bookmyshow.Models.User;

public class UserTransformer {
    public static User addUser(AddUserDto addUserDto){
        User user = User.builder()
                .name(addUserDto.getName())
                .age(addUserDto.getAge())
                .email(addUserDto.getEmail())
                .mobileNo(addUserDto.getMobileNo())
                .build();
        return user;
    }
    public static UserResponseDto convertEntityToDto(User user){
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .build();
        return userResponseDto;
    }
}
