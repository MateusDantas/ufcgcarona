package repository;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import com.avaje.ebean.Ebean;

import models.User;

@Singleton
public class UserEbeanRepository extends EbeanRepository<User>  {

	@Override
	public Set<User> get() {
		return User.find.all().stream().collect(Collectors.toSet());
	}

	@Override
	public boolean save(User entity) {
		Ebean.save(entity);
		return true;
	}

	@Override
	public boolean remove(User entity) {
		return Ebean.delete(entity);
	}

}
