package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;

import views.html.*;

import play.mvc.Security;

import javax.inject.Inject;

import models.User;
import play.data.Form;


public class Application extends Controller {

	@Inject private UserService userService;

	@Security.Authenticated(Secured.class)
	public Result index() {
		return ok(index.render("Your new application is ready."));
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
