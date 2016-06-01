package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;

import com.avaje.ebean.Finder;

@Entity
public class User extends Identity {

	private String name;
	private String registrationId; // matricula
	private String password;
	private String salt;
	private String course;

	public String validate() {
		Pattern pattern = Pattern.compile("11[0-9]{1}110[0-9]{3}");
		Matcher matcher = pattern.matcher(this.getRegistrationId());
		
		if (!matcher.matches())
			return "The registration id provided is invalid";
		if (this.getPassword().length() < 6)
			return "The password is invalid (must have at least 6 characters).";
		if (this.getName().length() < 3)
			return "The name provided is invalid (must have at least 3 characters).";
		if (this.getCourse().isEmpty())
			return "The course provided is invalid (must not be empty).";
		
		return null;
	}

	public static Finder<String, User> find = new Finder<String,User>(User.class);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
}
