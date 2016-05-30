package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.ValidateError;
import models.User;

public class UserValidator implements Validator<User> {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String REGISTRATION_ID_PATTERN = "11[0-9]{1}110[0-9]{3}";
	
	public UserValidator() {
		pattern = Pattern.compile(REGISTRATION_ID_PATTERN);
	}
	
	@Override
	public void validate(User user) throws ValidateError {
		matcher = pattern.matcher(user.getRegistrationId());
		if (!matcher.matches())
			throw new ValidateError("The registration id provided is invalid");
		if (user.getPassword().length() < 6)
			throw new ValidateError("The password is invalid (must have at least 6 characters).");
		if (user.getName().length() < 3)
			throw new ValidateError("The name provided is invalid (must have at least 3 characters).");
		if (user.getCourse().isEmpty())
			throw new ValidateError("The course provided is invalid (must not be empty).");
	}

}
