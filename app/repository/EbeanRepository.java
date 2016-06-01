package repository;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import models.Identity;

public abstract class EbeanRepository<T extends Identity> implements Repository<T> {

	@Override
	public Optional<T> get(String id) {
		return get().stream().filter(entity -> entity.getId().equals(id)).findFirst();
	}

	@Override
	public Set<T> query(Predicate<T> query) {
		return get().stream().filter(query).collect(Collectors.toSet());
	}
	
	@Override
	public boolean remove(String id) {
		T entity = this.get(id).get();
		return this.remove(entity);
	}
	
}
