package com.demo.booking.services;

import com.demo.booking.model.Theatre;
import com.demo.booking.model.MessageResponse;
import com.demo.booking.repo.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreServiceImpl {

    @Autowired
    TheatreRepository theatreRepository;

    @Transactional
    public ResponseEntity<?>  addTheatre(Theatre theatre){
        return new ResponseEntity<>(theatreRepository.save(theatre), HttpStatus.OK);
    }

    public List<Theatre> getAllTheatresByOwner(String userID){
        return theatreRepository.findByUserID(userID);
    }

    public List<Theatre> getAllTheatres(){
        return theatreRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> updateTheatre(Theatre theatre, String userID){
        Optional<Theatre> optional= theatreRepository.findById(theatre.getId());
        if(optional.isPresent()){
            Theatre updatedTheatre = optional.get();
            if(userID.equalsIgnoreCase(updatedTheatre.getUserID())) {
                updatedTheatre.setActive(theatre.isActive());
                updatedTheatre.setCity(theatre.getCity());
                updatedTheatre.setEmail(theatre.getEmail());
                updatedTheatre.setLatitude(theatre.getLatitude());
                updatedTheatre.setLongitude(theatre.getLongitude());
                updatedTheatre.setName(theatre.getName());
                updatedTheatre.setState(theatre.getState());
                updatedTheatre.setState(theatre.getPhone());
                return new ResponseEntity<>(theatreRepository.save(updatedTheatre), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<?> deleteTheatre(String theatreID, String userID){
        try {
            final Optional<Theatre> theatre = theatreRepository.findById(theatreID);
            if(theatre.isPresent() && userID.equalsIgnoreCase(theatre.get().getUserID())) {
                theatreRepository.deleteById(theatreID);
                return ResponseEntity.ok(new MessageResponse("Theatre was deleted successfully!"));
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> updateTheatreStatus(String theatreID, String status){
        Optional<Theatre> optional= theatreRepository.findById(theatreID);
        if(optional.isPresent()){
            Theatre theatre = optional.get();
            theatre.setStatus(status);
            return new ResponseEntity<>(theatreRepository.save(theatre), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
