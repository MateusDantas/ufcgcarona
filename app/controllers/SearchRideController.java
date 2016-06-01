package controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;


import exceptions.DatabaseError;
import exceptions.ValidateError;
import models.Driver;
import models.Passenger;
import models.Ride;
import models.RideSearchForm;
import models.Route;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.RideService;
import views.html.*;

@Security.Authenticated(Secured.class)
public class SearchRideController extends Controller {

	@Inject private RideService rideService;

	public Result search() {
		return ok(searchride.render(new HashSet<Ride>(), Form.form(RideSearchForm.class)));
	}
	
	public Result list() {
		Form<RideSearchForm> rideForm = Form.form(RideSearchForm.class).bindFromRequest();
		Set<Ride> filteredRides = rideService.getFilteredRides(rideForm.get().route, rideForm.get().day);
		return ok(searchride.render(filteredRides, Form.form(RideSearchForm.class)));
	}

}
