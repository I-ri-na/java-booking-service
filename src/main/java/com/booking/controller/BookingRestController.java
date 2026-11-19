package com.booking.controller;

import com.booking.entity.Booking;
import com.booking.entity.Client;
import com.booking.service.BookingService;
import com.booking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingRestController {

    @Autowired private BookingService bookingService;
    @Autowired private ClientService clientService;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Integer id) {
        Booking booking = bookingService.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @GetMapping("/clients/{clientId}/bookings")
    public ResponseEntity<List<Booking>> getClientBookings(@PathVariable Integer clientId) {
        if (clientService.getClientById(clientId) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bookingService.getBookingsByClientId(clientId));
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Client client = findClient(booking);
        if (client == null) return ResponseEntity.badRequest().build();

        booking.setId(null);
        booking.setClient(client);
        bookingService.saveBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Integer id, @RequestBody Booking booking) {
        Booking existing = bookingService.getBookingById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        if (booking.getClient() != null) {
            Client client = findClient(booking);
            if (client == null) return ResponseEntity.badRequest().build();
            existing.setClient(client);
        }
        existing.setCheckIn(booking.getCheckIn());
        existing.setCheckOut(booking.getCheckOut());
        existing.setRoomNumber(booking.getRoomNumber());
        existing.setStatus(booking.getStatus());

        bookingService.saveBooking(existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        if (bookingService.getBookingById(id) == null) return ResponseEntity.notFound().build();
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    private Client findClient(Booking booking) {
        if (booking.getClient() == null || booking.getClient().getId() == null) return null;
        return clientService.getClientById(booking.getClient().getId());
    }
}
