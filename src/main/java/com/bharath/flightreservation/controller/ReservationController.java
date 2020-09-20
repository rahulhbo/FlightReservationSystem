package com.bharath.flightreservation.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.FlightRepository;
import com.bharath.flightreservation.repos.ReservationRepository;
import com.bharath.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	private static final Logger LOGGER= org.slf4j.LoggerFactory.getLogger(ReservationController.class);
	
@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){
	LOGGER.info("Inside showCompleteReservation() invoked with the Flight Id"+flightId);
	Flight flight= flightRepository.findById(flightId).orElse(null);
	modelMap.addAttribute("flight",flight);
	LOGGER.info("Flight is:"+flight);
	
		return "completeReservation";
	}

@RequestMapping(value="/completeReservation",method=RequestMethod.POST)
public String completeReservation(ReservationRequest request,ModelMap modelMap)
{
	
	LOGGER.info("Inside completeReservation() "+request);
	
	Reservation reservation=reservationService.bookFlight(request);
	modelMap.addAttribute("msg","Reservation created successfully and the id is "+reservation.getId());

	return "reservationConfirmation";
}





}
