package services;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.time.DateUtils;

import exceptions.DatabaseError;
import exceptions.ValidateError;
import models.Passenger;
import models.Ride;
import models.Route;
import models.User;
import repository.Repository;

@Singleton
public class RealRideService implements RideService {

	@Inject
	private Repository<Ride> rideRepository;

	@Override
	public String registerRide(Ride ride) throws ValidateError, DatabaseError {
		String rideValidate = ride.validate();
		if (rideValidate != null) {
			throw new ValidateError(rideValidate);
		}
		boolean ok = this.rideRepository.save(ride);
		if (!ok) {
			throw new DatabaseError("Database Error");
		}
		return "Ride created successfully!";
	}

	@Override
	public Set<Ride> getAvailableRides() {
		return this.rideRepository.query(ride -> true);
	}

	@Override
	public Set<Ride> getUserRides(User user) {
		return this.rideRepository.query(ride -> this.isUserDriver(ride, user) || this.isUserPassenger(ride, user));
	}
	
	public boolean isUserDriver(Ride ride, User user) {
		return ride.getDriver().getRegistrationId().equals(user.getRegistrationId());
	}

	public boolean isUserPassenger(Ride ride, User user) {
		return ride.getPassengers().stream()
				.anyMatch(passenger -> user.getRegistrationId().equals(passenger.getRegistrationId()));
	}

	public Set<Ride> getFilteredRides(Route route, Date day) {
		return this.rideRepository
				.query(ride -> ride.getRoute().compare(route) && DateUtils.isSameDay(ride.getEstimatedArrival(), day));
	}

	@Override
	public String joinRide(Passenger passenger, Ride ride) {
		Optional<Ride> result = this.rideRepository.get(ride.getId());
		if (!result.isPresent()) {
			return "Invalid ride";
		}
		Ride realRide = result.get();
		if (this.isUserPassenger(realRide, passenger)) {
			return "You are already signed up to this ride";
		}
		if (this.isUserDriver(realRide, passenger)) {
			return "You can not be a passenger on your own ride";
		}
		if (realRide.getPassengers().size() >= realRide.getVacancy()) {
			return "This ride is full";
		}

		realRide.getPassengers().add(passenger);
		this.rideRepository.save(realRide);

		return "Successfully joined the ride";
	}

}
