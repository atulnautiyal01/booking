package com.demo.booking.controllers;


import com.demo.booking.model.Theatre;
import com.demo.booking.security.JwtUtility;
import com.demo.booking.services.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/theatre")
public class TheatreController {

    @Autowired
    TheatreServiceImpl theatreService;

    @Autowired
    private JwtUtility jwtUtils;

    @PostMapping("/add-theatre")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> addTheatre(@Valid @RequestBody Theatre theatre, HttpServletRequest request){
        String userID = jwtUtils.getUserDetailsFromJwtToken(request).get("id");
        theatre.setUserID(userID);
        theatre.setStatus("Pending");
        return theatreService.addTheatre(theatre);
    }

    @GetMapping("/get-all-theatres-by-owner")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> getAllTheatresByOwner(HttpServletRequest request){
        String userID = jwtUtils.getUserDetailsFromJwtToken(request).get("id");
        List<Theatre> theatreList = theatreService.getAllTheatresByOwner(userID);
        return ResponseEntity.ok(theatreList);
    }

    @GetMapping("/get-all-theatres")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllTheatres(){
        List<Theatre> theatreList = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatreList);
    }

    /**
     * Theater owner can update the theatre e.g. theatres can allocate seat inventory and update them for the show
     * @param theatre
     * @return
     */
    @PutMapping("/update-theatre")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> updateTheatre(@Valid @RequestBody Theatre theatre, HttpServletRequest request){
        String userID = jwtUtils.getUserDetailsFromJwtToken(request).get("id");
        return theatreService.updateTheatre(theatre, userID);
    }

    @DeleteMapping("/delete-theatre/{theatreID}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> deleteTheatre(@PathVariable String theatreID, HttpServletRequest request){
        String userID = jwtUtils.getUserDetailsFromJwtToken(request).get("id");
        return theatreService.deleteTheatre(theatreID, userID);
    }

    /**
     * Admin will approve the addition of a theatre
     * @param theatreID
     * @param status
     * @return
     */
    @PutMapping("/update-theatre-status/{theatreID}/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTheatreStatus(@PathVariable String theatreID, @PathVariable String status){
        return theatreService.updateTheatreStatus(theatreID,status);
    }

}
