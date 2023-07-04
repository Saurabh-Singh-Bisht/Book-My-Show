package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.Dtos.RequestDto.AddUserDto;
import com.example.bookmyshow.Dtos.ResponeDto.TicketResponseDto;
import com.example.bookmyshow.Dtos.ResponeDto.UserResponseDto;
import com.example.bookmyshow.Exceptions.NoUserFoundException;
import com.example.bookmyshow.Models.Ticket;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServices userServices;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody AddUserDto addUserDto){
        try {
            String str = userServices.add(addUserDto);
            return new ResponseEntity<>(str, HttpStatus.CREATED);
        }
        catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getOldestUser")
    public ResponseEntity<UserResponseDto> getOldestUser(){
        try {
            UserResponseDto userResponseDto = userServices.getOldestUser();
            userResponseDto.setStatusCode("200");
            userResponseDto.setStatusMessage("SUCCESS");

            return new ResponseEntity<>(userResponseDto, HttpStatus.FOUND);
        }
        catch (RuntimeException ex){
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setStatusCode("500");
            userResponseDto.setStatusMessage("Failure");
            return new ResponseEntity<>(userResponseDto, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findUserGreaterThanAAge")
    public ResponseEntity<List<User>> getUserGreaterThan(@RequestParam Integer age){
        List<User> userList = userServices.getUserGreaterThanAge(age);
        return new ResponseEntity<>(userList, HttpStatus.FOUND);
    }
    @GetMapping("/allTicketsHistoryOfAUser")
    public ResponseEntity<List<TicketResponseDto>> getAllTicketsBelongsToAUser(@RequestParam int userId){
        List<TicketResponseDto> ticketResponseDtos = userServices.getAllTickets(userId);
        return new ResponseEntity<>(ticketResponseDtos, HttpStatus.FOUND);
    }
}
