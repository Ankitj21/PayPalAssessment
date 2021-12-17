package com.paypal.bfs.test.bookingserv.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

@RestController
public class BookingResourceImpl implements BookingResource {

	@Autowired
	private BookingRepository bookingRepository;
	
    @Override
    public ResponseEntity<Booking> create(Booking booking) {
    	bookingRepository.save(booking);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}
}
