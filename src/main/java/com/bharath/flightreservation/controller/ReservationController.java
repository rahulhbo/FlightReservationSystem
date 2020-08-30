package com.bharath.flightreservation.controller;

import java.util.Optional;

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
	
@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){
	Flight flight= flightRepository.findById(flightId).orElse(null);
	modelMap.addAttribute("flight",flight);
		return "completeReservation";
	}

@RequestMapping(value="/completeReservation",method=RequestMethod.POST)
public String completeReservation(ReservationRequest request,ModelMap modelMap)
{
	Reservation reservation=reservationService.bookFlight(request);
	modelMap.addAttribute("msg","Reservation created successfully and the id is "+reservation.getId());

	return "reservationConfirmation";
}





}
