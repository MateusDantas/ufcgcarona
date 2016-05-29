package services;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;

import exceptions.DatabaseError;
import exceptions.InvalidUserData;
import models.User;
import repository.Repository;
import validators.Validator;

public class RealUserService implements UserService {

	private Repository<User> userRepository;
	private Validator<User> userValidator;
	
	@Inject
	public RealUserService(Repository<User> userRepository, Validator<User> userValidator) {
		this.userRepository = userRepository;
		this.userValidator = userValidator;
	}
	
	@Override
	public User registerUser(User user) throws InvalidUserData, DatabaseError {
		if (userValidator.validate(user)) {
			String salt = BCrypt.gensalt();
			String encryptedPassword = BCrypt.hashpw(user.getPassword(), salt);
			user.setSalt(salt);
			user.setPassword(encryptedPassword);
			boolean ok = this.userRepository.save(user);
			if (!ok) {
				throw new DatabaseError("Could not save entity to database");
			}
			return user;
		} else {
			throw new InvalidUserData("User input supplied is invalid!");
		}
	}

	@Override
	public User getUser(String id) {
		return this.userRepository.get(id).get();
	}

	@Override
	public Set<User> getUsers(Predicate<User> query) {
		return this.userRepository.query(query);
	}

	@Override
	public boolean authenticateUser(String registrationId, String password) throws NoSuchElementException {
		User user = this.userRepository.query(
				entity -> entity.getRegistrationId() == registrationId).iterator().next();
		return BCrypt.checkpw(password, user.getPassword());
	}

}
