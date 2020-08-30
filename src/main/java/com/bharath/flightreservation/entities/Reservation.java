package com.bharath.flightreservation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Reservation extends AbstractEntity {

	private Boolean checkedIn;
    @Column(name = "NUMBER_OF_BAG")
	private int number_Of_Bag;
	@OneToOne
	private Passenger passenger;
	@OneToOne
	private Flight flight;
	
	
	
	public Boolean getCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	public int getNumberofBags() {
		return number_Of_Bag;
	}
	public void setNumberofBags(int numberofBags) {
		this.number_Of_Bag = numberofBags;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
