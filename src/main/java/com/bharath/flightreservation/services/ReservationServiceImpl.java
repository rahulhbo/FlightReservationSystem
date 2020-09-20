package com.bharath.flightreservation.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharath.flightreservation.controller.ReservationController;
import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Passenger;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.FlightRepository;
import com.bharath.flightreservation.repos.PassengerRepository;
import com.bharath.flightreservation.repos.ReservationRepository;
import com.bharath.flightreservation.util.EmailUtil;
import com.bharath.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepository;
	@Autowired
	PassengerRepository passengerrepository;
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	private static final Logger LOGGER= org.slf4j.LoggerFactory.getLogger(ReservationController.class);
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("Inside bookFlight()");
		Long flightId = request.getFlightId();
		Flight flight=flightRepository.findById(flightId).orElse(null);
		
		Passenger passenger= new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Save the Passanger :"+passenger);
		Passenger savedPassenger= passengerrepository.save(passenger);
		
		Reservation reservation=new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving the Reservation:"+reservation);
		Reservation savedReservation =reservationRepository.save(reservation);
		String filePath = "C:/Users/Admin/Documents/reservations/reservation"+savedReservation.getId()+".pdf";
		LOGGER.info("Generating the itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Emaling the Itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return savedReservation;
	}

}
