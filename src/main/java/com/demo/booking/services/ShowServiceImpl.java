package com.demo.booking.services;

import com.demo.booking.model.Show;
import com.demo.booking.model.Theatre;
import com.demo.booking.model.MessageResponse;
import com.demo.booking.repo.ShowRepository;
import com.demo.booking.repo.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImpl {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    TheatreRepository theatreRepository;

    public String addShow(Show theatre){
        showRepository.save(theatre);
        return "success";
    }

    public List<Show> getAllShowsByTheatre(String theatreID){
        return showRepository.findByTheatreID(theatreID);
    }

    public List<Show> getAllShowsByMovie(String movieID){
        return showRepository.findByMovieID(movieID);
    }

    public Show getShow(String showID){
        Optional<Show> show = showRepository.findById(showID);
        return show.get();
    }

    public ResponseEntity<?> updateShow(Show show, String userID){
        final Optional<Theatre> optional = theatreRepository.findById(show.getTheatreID());
        if(optional.isPresent() && userID.equalsIgnoreCase(optional.get().getUserID())){
            return new ResponseEntity<>( showRepository.save(show), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Transactional
    public ResponseEntity<?> deleteShow(String showID, String userID){
        try {
            final Optional<Show> optionalShow = showRepository.findById(showID);
            if(optionalShow.isPresent()){
                final Optional<Theatre> optionalTheatre = theatreRepository.findById(optionalShow.get().getTheatreID());
                if(optionalTheatre.isPresent() && userID.equalsIgnoreCase(optionalTheatre.get().getUserID())){
                    showRepository.deleteById(showID);
                    return ResponseEntity.ok(new MessageResponse("show was deleted successfully!"));
                }else{
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
