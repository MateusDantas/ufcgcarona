package controllers;

import java.util.HashSet;

import javax.inject.Inject;

import models.Passenger;
import models.Ride;
import models.RideSearchForm;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.RideService;
import views.html.*;

@Security.Authenticated(Secured.class)
public class JoinRideController extends Controller {

	@Inject private RideService rideService;
	
	public Result join(String id) {
		Logger.debug("trying to join " + id);
		Ride ride = new Ride();
		ride.setId(id);
		Passenger passenger = new Passenger();
		passenger.setRegistrationId(request().username());
		
		String result = rideService.joinRide(passenger, ride);
		flash("success", result);
		Logger.debug("result is " + result);
		return redirect(routes.Application.index());
	}
}
