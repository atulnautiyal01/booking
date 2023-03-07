package com.demo.booking.controllers;

import com.demo.booking.model.Show;
import com.demo.booking.model.MessageResponse;
import com.demo.booking.security.JwtUtility;
import com.demo.booking.services.ShowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/show")
public class ShowController {
    @Autowired
    ShowServiceImpl showService;

    @Autowired
    private JwtUtility jwtUtils;

    /**
     * Theater can create show for the day
     * @param show
     * @return
     */
    @PostMapping("/add-show")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Show show){
        show.setSelectedSeats(new int[0]);
        show.setBookedSeats(new int[0]);
        showService.addShow(show);
        return ResponseEntity.ok(new MessageResponse("show was added successfully!"));
    }

    @GetMapping("/get-all-shows-by-theatre-id/{theatreID}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllShowsByTheater(@PathVariable String theatreID){
        List<Show> showList = showService.getAllShowsByTheatre(theatreID);
        return ResponseEntity.ok(showList);
    }

    /**
     * Browse theatres currently running the show (movie selected) in the town, including show timing by a chosen date
     *
     * @param movieID
     * @return
     */
    @GetMapping("/get-all-shows-by-movie-id/{movieID}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllShowsByMovie(@PathVariable String movieID){
        List<Show> showList = showService.getAllShowsByMovie(movieID);
        return ResponseEntity.ok(showList);
    }


    @GetMapping("/get-show-by-show-id/{showID}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
    public ResponseEntity<?> getShowByShowID(@PathVariable String showID){
        Show show = showService.getShow(showID);
        return ResponseEntity.ok(show);
    }

    /**
     * Theatre can update show for the day
     *
     * @param show
     * @return
     */
    @PutMapping("/update-show")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> updateShow(@Valid @RequestBody Show show, HttpServletRequest request){
        String userID = jwtUtils.getUserDetailsFromJwtToken(request).get("id");
        showService.updateShow(show, userID);
        return ResponseEntity.ok(new MessageResponse("show was updated successfully!"));
    }

    /**
     * Theater can delete show for  the day
     *
     * @param showID
     * @return
     */
    @DeleteMapping("/delete-show/{showID}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> deleteShow(@PathVariable String showID, HttpServletRequest request){
        String userID = jwtUtils.getUserDetailsFromJwtToken(request).get("id");
        return showService.deleteShow(showID, userID);
    }
}
