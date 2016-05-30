package controllers;

import javax.inject.Inject;

import exceptions.DatabaseError;
import exceptions.UserAlreadyRegistered;
import exceptions.ValidateError;
import models.User;
import views.html.*;
import play.*;
import play.mvc.*;
import play.data.Form;
import services.UserService;

public class SignupController extends Controller {

	@Inject private UserService userService;
	
	public Result signup() {
		return ok(signup.render());
	}
	
	public Result register() {
		Form<User> form = Form.form(User.class).bindFromRequest();
		try {
			this.userService.registerUser(form.get());
		} catch (ValidateError e) {
			flash("error", e.getMessage());
			return redirect(routes.SignupController.signup());
		} catch (DatabaseError e) {
			flash("error", e.getMessage());
			return redirect(routes.SignupController.signup());
		} catch (UserAlreadyRegistered e) {
			flash("error", e.getMessage());
			return redirect(routes.SignupController.signup());
		}
		flash("success", "Successfully registered!");
		return redirect(routes.Application.login());
	}
}
