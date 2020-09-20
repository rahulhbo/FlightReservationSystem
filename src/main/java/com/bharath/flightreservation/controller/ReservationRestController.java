package com.bharath.flightreservation.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.flightreservation.dto.ReservationUpdateRequest;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.ReservationRepository;
import com.bharath.flightreservation.util.PDFGenerator;

@RestController
public class ReservationRestController {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	
	@Autowired
	private static final Logger LOGGER= org.slf4j.LoggerFactory.getLogger(ReservationRestController.class);
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id")Long id)
	{
		LOGGER.info("Inside findReservation()"+id);
	return reservationRepository.findById(id).orElse(null);
	}
	
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request)
	{
		LOGGER.info("Inside updateReservation() for"+request);
		Reservation reservation=reservationRepository.findById(request.getId()).orElse(null);
		reservation.setNumberofBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving Reservation");
	return reservationRepository.save(reservation);
	}
	

}
