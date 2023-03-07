package com.demo.booking.repo;

import com.demo.booking.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie,String> {
}
