package com.demo.booking.controllers;

import com.demo.booking.model.Booking;
import com.demo.booking.security.JwtUtility;
import com.demo.booking.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/booking")
public class BookController {
    @Autowired
    BookingServiceImpl bookingService;

    @Autowired
    private JwtUtility jwtUtils;

    /**
     * Book movie tickets by selecting a theatre, timing, and preferred seats for the day
     * user can do bulk booking by selecting multiple movies and seats
     *
     * @param bookings
     * @return
     */
    @PostMapping("/book-show")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> bookShow(@Valid @RequestBody List<Booking> bookings){
        return bookingService.bookShow(bookings);
    }

    @PostMapping("/cancel-booking")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> cancelBookings(@Valid @RequestBody List<String> bookingIDs, HttpServletRequest request){
        String userID = jwtUtils.getUserDetailsFromJwtToken(request).get("id");
        return bookingService.cancelBooking(bookingIDs,userID);
    }

    @GetMapping("/get-all-bookings-by-user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllBookingsByUser(HttpServletRequest request){
        String userID = jwtUtils.getUserDetailsFromJwtToken(request).get("id");
        List<Booking> bookingList = bookingService.getAllBookingsByUser(userID);
        return ResponseEntity.ok(bookingList);
    }
}
