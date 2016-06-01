package services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.mindrot.jbcrypt.BCrypt;

import exceptions.DatabaseError;
import exceptions.UserAlreadyRegistered;
import exceptions.ValidateError;
import models.User;
import play.Logger;
import repository.Repository;

@Singleton
public class RealUserService implements UserService {

	private Repository<User> userRepository;

	@Inject
	public RealUserService(@Named("UserRepository") Repository<User> userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User registerUser(User user) throws ValidateError, DatabaseError, UserAlreadyRegistered {
		if (this.getByRegistrationId(user.getRegistrationId()).isPresent()) {
			throw new UserAlreadyRegistered("There is a user already registered with that registration id");
		}
		String validateStr = user.validate();
		if (validateStr != null) {
			throw new ValidateError(validateStr);
		}
		String salt = BCrypt.gensalt();
		String encryptedPassword = BCrypt.hashpw(user.getPassword(), salt);
		user.setSalt(salt);
		user.setPassword(encryptedPassword);
		boolean ok = this.userRepository.save(user);
		if (!ok) {
			throw new DatabaseError("Could not save entity to database");
		}
		return user;
	}

	@Override
	public User getUser(String id) {
		return this.userRepository.get(id).get();
	}

	@Override
	public Optional<User> getByRegistrationId(String registrationId) {
		Optional<User> result = Optional.empty();
		try {
			User user = this.userRepository.query(entity -> registrationId.equals(entity.getRegistrationId())).iterator()
					.next();
			result = Optional.of(user);
		} catch (NoSuchElementException e) {
		}

		return result;
	}

	@Override
	public Set<User> getUsers(Predicate<User> query) {
		return this.userRepository.query(query);
	}

	@Override
	public boolean authenticateUser(String registrationId, String password) {
		Optional<User> result = this.getByRegistrationId(registrationId);
		if (!result.isPresent()) {
			return false;
		}
		return BCrypt.checkpw(password, result.get().getPassword());
	}

}
