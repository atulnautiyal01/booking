package com.demo.booking.repo;

import com.demo.booking.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShowRepository extends MongoRepository<Show,String> {
    List<Show> findByTheatreID(String theatreID);

    List<Show> findByMovieID(String movieID);

}
