package services;

import java.util.Date;
import java.util.Set;

import exceptions.DatabaseError;
import exceptions.ValidateError;
import models.Passenger;
import models.Ride;
import models.Route;
import models.User;

public interface RideService {

	String registerRide(Ride ride) throws ValidateError, DatabaseError;
	
	Set<Ride> getAvailableRides();
	
	Set<Ride> getUserRides(User user);
	
	Set<Ride> getFilteredRides(Route route, Date day);
	
	String joinRide(Passenger passenger, Ride ride);
	
}
