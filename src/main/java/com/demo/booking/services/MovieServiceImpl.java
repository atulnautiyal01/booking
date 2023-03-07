package com.demo.booking.services;

import com.demo.booking.model.Movie;
import com.demo.booking.model.Show;
import com.demo.booking.model.MessageResponse;
import com.demo.booking.repo.MovieRepository;
import com.demo.booking.repo.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceImpl {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    @Transactional
    public ResponseEntity<?> addMovie(Movie movie){
        return new ResponseEntity<>(movieRepository.save(movie), HttpStatus.OK);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> updateMovie(Movie movie){
        Optional<Movie> optional= movieRepository.findById(movie.getId());
        if(optional.isPresent()){
            return new ResponseEntity<>(movieRepository.save(movie), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<?>  deleteMovie(String movieID){
        List<Show> showList =  showRepository.findByMovieID(movieID);
        if(Objects.nonNull(showList) && showList.size()==0) {
            movieRepository.deleteById(movieID);
            return ResponseEntity.ok(new MessageResponse("Movie was deleted successfully!"));
        }else{
            return new ResponseEntity<>("Unable to delete movie, a show is already linked to this movie!" , HttpStatus.CONFLICT);
        }
    }


}
