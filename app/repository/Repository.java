package repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import models.Entity;

public interface Repository<T extends Entity> {

	Optional<T> get(String id);
	
	Set<T> query(Predicate<T> query);
	
	void save(T entity);
	
	void save(Collection<T> entities);
	
	void remove(T entity);
	
	void remove(Collection<T> entities);
	
	void remove(String id);
}
