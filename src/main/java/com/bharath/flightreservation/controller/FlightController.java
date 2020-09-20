package com.bharath.flightreservation.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repos.FlightRepository;
import java.util.*;

@Controller
public class FlightController {
	
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	private static final Logger LOGGER= org.slf4j.LoggerFactory.getLogger(FlightController.class);
	
	@RequestMapping("findFlights")
	public String findFlight(@RequestParam("from") String from,@RequestParam("to") String to,@RequestParam ("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate ,ModelMap modelMap)
	{
		LOGGER.info("Inside findFlight( )"+from+"TO"+to+"Date of Departure"+departureDate);
	System.out.println("iamhere====>"+departureDate);
	List<Flight> flights=	flightRepository.findByDepartureCityAndArrivalCityAndDateOfDeparture(from, to, departureDate);
	modelMap.addAttribute("flights",flights);
	LOGGER.info("Flight Found are"+flights);
		return "displayFlights";
	
	}
}
