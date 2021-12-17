package com.paypal.bfs.test.bookingserv.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.impl.Address;
import com.paypal.bfs.test.bookingserv.impl.BookingRepository;
import com.paypal.bfs.test.bookingserv.impl.BookingResourceImpl;

@WebMvcTest(BookingResourceImpl.class)
public class BookingResourceImplTest {
	
	@Autowired
    MockMvc mockMvc;
	
	@MockBean
	BookingRepository bookingRepository;
    
    @Test
    public void getAllBookingsTest() throws Exception {    	
    	String uri = "/bfs";
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    	
    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);    	
    	List<Booking> list = bookingRepository.findAll();    	
    	assertTrue(list.size() > 0);    	
    }
    
    @Test
    public void createBookingTest() throws Exception {
    	String uri = "/bfs";
    	
    	Address address = new Address();
    	address.setLine1("Bellandur");
    	address.setCity("Bangalore");
    	address.setState("Karnataka");
    	address.setZip_code("560045");
    	
    	Booking booking = new Booking();    	
    	booking.setFirstName("Ram");
    	booking.setLastName("Shyam");
    	booking.setDeposit(1000);
    	booking.setTotalprice(50000);
    	booking.setDateOfBirth("22/02/2021");
    	booking.setAddress(address);
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(new Gson().toJson(booking))).andReturn();
    	
    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(201, status);
    }
}
