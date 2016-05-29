package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.User;

public class UserValidator implements Validator<User> {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String REGISTRATION_ID_PATTERN = "11[0-9]{1}110[0-9]{3}";
	
	public UserValidator() {
		pattern = Pattern.compile(REGISTRATION_ID_PATTERN);
	}
	
	@Override
	public boolean validate(User user) {
		matcher = pattern.matcher(user.getRegistrationId());
		if (!matcher.matches())
			return false;
		if (user.getPassword().length() < 6)
			return false;
		if (user.getName().length() < 3)
			return false;
		if (user.getCourse().isEmpty())
			return false;
		return true;
	}

}
