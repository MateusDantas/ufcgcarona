package repository;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import models.Identity;

public interface Repository<T extends Identity> {
	
	Set<T> get();
	
	Optional<T> get(String id);
	
	Set<T> query(Predicate<T> query);
	
	boolean save(T entity);
	
	boolean remove(T entity);
	
	boolean remove(String id);
}
