package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.TicketRequestDto;
import com.example.bookmyshow.Dtos.ResponeDto.TicketResponseDto;
import com.example.bookmyshow.Enums.TicketStatus;
import com.example.bookmyshow.Exceptions.NoUserFoundException;
import com.example.bookmyshow.Exceptions.ShowNotFound;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.ShowSeats;
import com.example.bookmyshow.Models.Ticket;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TicketRepository;
import com.example.bookmyshow.Repository.UserRepository;
import com.example.bookmyshow.Transformers.ShowTransformer;
import com.example.bookmyshow.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    JavaMailSender javaMailSender;
    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto)throws NoUserFoundException, ShowNotFound{
        Optional<User> userOptional = userRepository.findById(ticketRequestDto.getUserId());
        if(userOptional.isEmpty()){
            throw new NoUserFoundException("Invalid User Id");
        }
        Optional<Show> showOptional = showRepository.findById(ticketRequestDto.getShowId());
        if(showOptional.isEmpty()){
            throw new ShowNotFound("Show not found");
        }
        User user = userOptional.get();
        Show show = showOptional.get();

        int price = validationRequestAvailability(ticketRequestDto.getRequestedSeats(), show);
        if(price == -1){
            throw new RuntimeException("Requested seats entered are incorrect");
        }
        Ticket ticket = new Ticket();
        //Calculate & set price
        ticket.setTotalTicketPrice(price);
        ticket.setBookedSeats(ShowTransformer.convertListToString(ticketRequestDto.getRequestedSeats()));
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setTicketStatus(TicketStatus.BOOKED);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        userRepository.save(user);

        show.getTicketList().add(ticket);
        showRepository.save(show);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String mailBody = "Hi " + user.getName() + "," + "\n" +
                "Your Ticket(s) has been Confirmed" + "\n" +
                "Booked Seat's: " + ticket.getBookedSeats() + "\n" +
                "Movie name: " + show.getMovie().getMovieName() + "\n" +
                "Time: " + show.getTime() + "\n" +
                "Date: " + show.getDate() + "\n" +
                "Theater Name: " + show.getTheater().getName() + "\n" +
                "Location: " + show.getTheater().getLocation();
        simpleMailMessage.setSubject("Ticket's Confirmation");
        simpleMailMessage.setFrom("spring.test750@gmail.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setText(mailBody);

        javaMailSender.send(simpleMailMessage);

        return TicketTransformer.generateTicket(show, ticket);
    }

    private int validationRequestAvailability(List<String> reqSeats, Show show) {
        List<ShowSeats> showSeatsList = show.getShowSeatsList();
        int price =0;
        for (ShowSeats showSeats: showSeatsList){
            String seatNo = showSeats.getSeatNo();
            if(reqSeats.contains(seatNo)){
                if (showSeats.isAvailable() == false)
                    return -1;
                else {
                    price += showSeats.getPrice();
                    showSeats.setAvailable(false);
                }
            }
        }
        return price;
    }

    public String cancelTicket(int ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        Show show = ticket.getShow();
        List<ShowSeats> showSeatsList = show.getShowSeatsList();
        String[] arr = ticket.getBookedSeats().split(", ");
        for(String s: arr){
            String str = s;
            for(ShowSeats showSeats: showSeatsList){
                if(showSeats.getSeatNo().equals(str)){
                    showSeats.setAvailable(Boolean.TRUE);
                    break;
                }
            }
        }
        ticket.setTicketStatus(TicketStatus.CANCELED);
        showRepository.save(show);
        return "Tickets Canceled";

    }
}
