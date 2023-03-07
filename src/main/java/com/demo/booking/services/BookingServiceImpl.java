package com.demo.booking.services;

import com.demo.booking.model.Booking;
import com.demo.booking.model.EnumBookingStatus;
import com.demo.booking.model.Show;
import com.demo.booking.model.MessageResponse;
import com.demo.booking.repo.BookingRepository;
import com.demo.booking.repo.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Autowired
    private ShowServiceImpl showService;



    @Transactional
    public ResponseEntity<?> bookShow(@NotNull List<Booking> bookings){
        Map<String, Show> bufferShowsMap = new HashMap<>();
        double totalAmount = 0;
        //initially reserve the seat before confirming
        bookings.forEach(e-> {
            e.setEnumBookingStatus(EnumBookingStatus.RESERVED);
            Booking booking = bookingRepository.save(e);
        });

        //total ticket price
        //update selected seats
        for (Booking booking: bookings) {
            Optional<Show> show = showRepository.findById(booking.getShowID());
            if(show.isPresent()){
                Show updatedShow = show.get();
                //update map with original show details
                bufferShowsMap.put(updatedShow.getId(),updatedShow);
                totalAmount += updatedShow.getTicketPrice()* booking.getSeats().length;
                int[] selectedSeats = updatedShow.getSelectedSeats();
                int length1= selectedSeats.length;
                int[] seatsToSelect = booking.getSeats();
                int length2 = seatsToSelect.length;
                int[] mergedArray = new int[length1+length2];
                System.arraycopy(selectedSeats, 0, mergedArray, 0, length1);
                System.arraycopy(seatsToSelect, 0, mergedArray, length1, length2);
                updatedShow.setSelectedSeats(mergedArray);
                showRepository.save(updatedShow);
            }else {
                totalAmount += 0;
            }
        }
        final double totalTicketPrice = totalAmount;

        //let payment be handled in async way
        Callable<Boolean> task = new Callable<Boolean>() {
            public Boolean call() {
                return true;
            }
        };

        //check the payment status
        Future<Boolean> future = executor.submit(task);
        try {
            Boolean result = future.get(10, TimeUnit.MINUTES);
        } catch (Exception ex) {
            bookings.forEach(e-> {
                e.setEnumBookingStatus(EnumBookingStatus.EXPIRED);
                Booking booking = bookingRepository.save(e);
                //remove or revert seat selection
                showRepository.save(bufferShowsMap.get(e.getShowID()));
            });
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        bookings.forEach(e-> {
            e.setEnumBookingStatus(EnumBookingStatus.BOOKED);
            Booking booking = bookingRepository.save(e);
            Optional<Show> show = showRepository.findById(booking.getShowID());
            if(show.isPresent()){
                Show updatedShow = show.get();
                int[] bookedSeats = updatedShow.getBookedSeats();
                int length1= bookedSeats.length;
                int[] seatsToBook = booking.getSeats();
                int length2 = seatsToBook.length;
                int[] mergedArray = new int[length1+length2];
                System.arraycopy(bookedSeats, 0, mergedArray, 0, length1);
                System.arraycopy(seatsToBook, 0, mergedArray, length1, length2);
                updatedShow.setBookedSeats(mergedArray);
                showRepository.save(updatedShow);
            }
        });

        return ResponseEntity.ok(new MessageResponse("Tickets were booked successfully!"));
    }

    @Transactional
    public ResponseEntity<?> cancelBooking(List<String> bookingIDs, String userID){
        try {
            List<String> filteredBookingIDs = bookingIDs.stream().filter(e->e.equalsIgnoreCase(userID)).collect(Collectors.toList());
            bookingRepository.deleteAllById(filteredBookingIDs);
            return ResponseEntity.ok(new MessageResponse("shows were deleted successfully!"));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Booking> getAllBookingsByUser(String userID){
        return bookingRepository.findByUserID(userID);
    }

}
