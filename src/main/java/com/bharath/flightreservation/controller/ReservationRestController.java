package com.bharath.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.flightreservation.dto.ReservationUpdateRequest;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id")Long id)
	{
	return reservationRepository.findById(id).orElse(null);
	}
	
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request)
	{
		Reservation reservation=reservationRepository.findById(request.getId()).orElse(null);
		reservation.setNumberofBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		
	return reservationRepository.save(reservation);
	}
	

}
