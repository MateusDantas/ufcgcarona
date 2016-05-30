package services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import exceptions.DatabaseError;
import exceptions.UserAlreadyRegistered;
import exceptions.ValidateError;
import models.User;

public interface UserService {

	User registerUser(User user) throws ValidateError, DatabaseError, UserAlreadyRegistered;

	User getUser(String id);
	
	Set<User> getUsers(Predicate<User> query);
	
	Optional<User> getByRegistrationId(String registrationId);
	
	boolean authenticateUser(String email, String password);
	
}
