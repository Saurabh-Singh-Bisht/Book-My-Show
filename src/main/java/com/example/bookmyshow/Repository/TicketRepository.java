package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "select * from ticket where user_id = :userId", nativeQuery = true)
    List<Ticket> getTicketsHistory(int userId);

    @Query(value = "select sum(total_ticket_price) from ticket where show_id = :id AND ticket_status = \"BOOKED\"", nativeQuery = true)
    int getRevenue(int id);
}
