package controllers;

import java.util.HashSet;

import javax.inject.Inject;


import exceptions.DatabaseError;
import exceptions.ValidateError;
import models.Driver;
import models.Passenger;
import models.Ride;
import play.Logger;
import play.data.Form;
import play.data.format.Formatters;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.RideService;
import views.html.*;

import java.util.Date;

@Security.Authenticated(Secured.class)
public class CreateRideController extends Controller {

	@Inject private RideService rideService;
	
	public CreateRideController() {
	}
	public Result create() {
		return ok(createride.render(Form.form(Ride.class)));
	}
	
	public Result register() {
		Form<Ride> rideForm = Form.form(Ride.class).bindFromRequest();
		try {
			Ride ride = rideForm.get();
			Driver driver = new Driver();
			driver.setRegistrationId(request().username());
			ride.setDriver(driver);
			ride.setPassengers(new HashSet<Passenger>());
			String message = rideService.registerRide(rideForm.get());
			flash("success", message);
		} catch (ValidateError err) {
			flash("error", err.getMessage());
		} catch (DatabaseError err) {
			flash("error", err.getMessage());
		}
		return redirect(routes.CreateRideController.create());
	}

}
