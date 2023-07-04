package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.AddUserDto;
import com.example.bookmyshow.Dtos.ResponeDto.TicketResponseDto;
import com.example.bookmyshow.Dtos.ResponeDto.UserResponseDto;
import com.example.bookmyshow.Exceptions.NoUserFoundException;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.Ticket;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Repository.TicketRepository;
import com.example.bookmyshow.Repository.UserRepository;
import com.example.bookmyshow.Transformers.TicketTransformer;
import com.example.bookmyshow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;

    public String add(AddUserDto addUserDto) {
        User user = UserTransformer.addUser(addUserDto);
        userRepository.save(user);
        return "User created successfully";
    }

    public UserResponseDto getOldestUser() throws RuntimeException{
        List<User> userList = userRepository.findAll();
        int maxAge = 0;
        User userAns = null;
        for (User user: userList){
            if(user.getAge() > maxAge){
                maxAge = user.getAge();
                userAns = user;
            }
        }
        if (userAns == null){
            throw new NoUserFoundException("No user found");
        }
        UserResponseDto userResponseDto = UserTransformer.convertEntityToDto(userAns);
        return userResponseDto;
    }

    public List<User> getUserGreaterThanAge(Integer age) {
        List<User> userList = userRepository.findUserWithAgeGreater(age);
        return userList;
    }

    public List<TicketResponseDto> getAllTickets(int userId) {
        List<Ticket> ticketList = ticketRepository.getTicketsHistory(userId);
        List<TicketResponseDto> ticketResponseDtos = new ArrayList<>();
        for (Ticket ticket: ticketList){
            Show show = ticket.getShow();
            ticketResponseDtos.add(TicketTransformer.generateTicket(show, ticket));
        }
        return ticketResponseDtos;
    }
}
