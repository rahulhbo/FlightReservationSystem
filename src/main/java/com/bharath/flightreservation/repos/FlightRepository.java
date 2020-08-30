package com.bharath.flightreservation.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bharath.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	//List<Flight> findByDEPARTURE_CITYAndARRIVAL_CITYAndDATE_OF_DEPARTURE(String from, String to, Date departureDate);

	//List<Flight> findByDepartureCityAndArrivalCityAndDateOfDeparture(String from, String to, Date departureDate);

	List<Flight> findByDepartureCityAndArrivalCityAndDateOfDeparture(String from, String to, Date departureDate);
	
	/*
	 * @Query(
	 * value="from Fligth where departureCity=:departureCity and arrivalCity=:arrivalCity and dateofDeparture=:dateofDeparture"
	 * ,nativeQuery = true) List<Flight> findFlights(@Param("departureCity")String
	 * from,@Param("arrivalCity") String to,@Param("dateofDeparture") Date
	 * departureDate);
	 */


}
