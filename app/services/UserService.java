package services;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;

import exceptions.DatabaseError;
import exceptions.InvalidUserData;
import models.User;

public interface UserService {

	User registerUser(User user) throws InvalidUserData, DatabaseError;

	User getUser(String id);
	
	Set<User> getUsers(Predicate<User> query);
	
	boolean authenticateUser(String email, String password) throws NoSuchElementException;
	
}
