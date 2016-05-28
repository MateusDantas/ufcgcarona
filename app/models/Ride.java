package models;

import java.util.Date;

public class Ride {

	private Route route;
	private Passenger passenger;
	private Driver driver;
	
	private Date estimatedDeparture;
	private Date estimatedArrival;
	
	private int rideCost;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Date getEstimatedDeparture() {
		return estimatedDeparture;
	}

	public void setEstimatedDeparture(Date estimatedDeparture) {
		this.estimatedDeparture = estimatedDeparture;
	}

	public Date getEstimatedArrival() {
		return estimatedArrival;
	}

	public void setEstimatedArrival(Date estimatedArrival) {
		this.estimatedArrival = estimatedArrival;
	}

	public int getRideCost() {
		return rideCost;
	}

	public void setRideCost(int rideCost) {
		this.rideCost = rideCost;
	}
	
	
}
