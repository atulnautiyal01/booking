package com.demo.booking.repo;

import com.demo.booking.model.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TheatreRepository extends MongoRepository<Theatre,String> {
    List<Theatre> findByUserID(String UserID);
}
