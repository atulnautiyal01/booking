package com.demo.booking.controllers;

import com.demo.booking.model.Movie;
import com.demo.booking.security.JwtUtility;
import com.demo.booking.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    private JwtUtility jwtUtils;

    @PostMapping("/add-movie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    @GetMapping("/get-all-movies")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllMovies(){
        List<Movie> theatreList = movieService.getAllMovies();
        return ResponseEntity.ok(theatreList);
    }

    @PutMapping("/update-movie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateMovie(@Valid @RequestBody Movie movie){
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/delete-movie/{movieID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMovie(@PathVariable String movieID){
        return movieService.deleteMovie(movieID);
    }
}
