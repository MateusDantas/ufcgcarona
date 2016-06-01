package models;

import java.util.Date;
import java.util.Set;

import play.data.format.Formats;

public class Ride extends Entity {

	public Route route;
	public Set<Passenger> passengers;
	public Driver driver;

	public Date estimatedDeparture;
	public Date estimatedArrival;
	
	public int vacancy;
	public int rideCost;
		
	public String validate() {
		return null;
	}
	
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Set<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<Passenger> passengers) {
		this.passengers = passengers;
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

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	
	
}
