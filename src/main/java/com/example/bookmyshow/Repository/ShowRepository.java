package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Dtos.ResponeDto.ShowResponseDto;
import com.example.bookmyshow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    @Query(value = "select * from shows where movie_id = :movieId", nativeQuery = true)
    List<Show> findByMovieId(int movieId);

    @Query(value = "select movie_id from shows where date = :date group by movie_id order by count(*) desc limit 1", nativeQuery = true)
    int getMovieWithMostShows(Date date);

    @Query(value = "select * from shows where theater_id = :theaterId AND movie_id = :movieId", nativeQuery = true)
    List<Show> getShowList(int theaterId, int movieId);
}
