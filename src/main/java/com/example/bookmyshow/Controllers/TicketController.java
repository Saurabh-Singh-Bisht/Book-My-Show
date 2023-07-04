package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.Dtos.RequestDto.TicketRequestDto;
import com.example.bookmyshow.Dtos.ResponeDto.TicketResponseDto;
import com.example.bookmyshow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/book-ticket")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketRequestDto ticketRequestDto){
        try{
            TicketResponseDto ticketResponseDto =ticketService.bookTicket(ticketRequestDto);
            return new ResponseEntity<>(ticketResponseDto, HttpStatus.CREATED);
        }
        catch (RuntimeException ex){
            throw new RuntimeException(ex);
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/cancel-ticket")
    public ResponseEntity<String> cancelTicket(@RequestParam int ticketId){
        return new ResponseEntity<>(ticketService.cancelTicket(ticketId), HttpStatus.FOUND);
    }
}
