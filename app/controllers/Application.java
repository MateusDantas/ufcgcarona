package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.RideService;
import services.UserService;

import views.html.*;

import play.mvc.Security;

import javax.inject.Inject;

import models.Driver;
import models.Ride;
import models.User;
import play.data.Form;


public class Application extends Controller {

	@Inject private UserService userService;
	@Inject private RideService rideService;

	@Security.Authenticated(Secured.class)
	public Result index() {
		Driver driver = new Driver();
		driver.setRegistrationId(request().username());
		return ok(index.render(rideService.getUserRides(driver), Form.form(Ride.class)));
	}

	public Result login() {
		return ok(login.render());
	}

	public Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (userService.authenticateUser(loginForm.get().registrationId, loginForm.get().password)) {
			flash("success", "Authentication successfull");
			session().clear();
			session("registrationId", loginForm.get().registrationId);
			return redirect(routes.Application.index());
		} else {
			flash("error", "Failed authentication");
			return redirect(routes.Application.login());
		}
	}

	public static class Login {
		public String registrationId;
		public String password;
	}

}
