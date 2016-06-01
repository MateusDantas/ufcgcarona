package repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import models.Identity;

public abstract class InMemoryRepository<T extends Identity> implements Repository<T> {

	private Set<T> memory = new HashSet<>();
	
	@Override
	public Set<T> get() {
		return memory;
	}
	
 	@Override
	public Optional<T> get(String id) {
		return memory.stream().filter(entity -> entity.getId().equals(id)).findFirst();
	}

	@Override
	public Set<T> query(Predicate<T> query) {
		return memory.stream().filter(query).collect(Collectors.toSet());
	}

	@Override
	public boolean save(T entity) {
		if (memory.contains(entity))
			memory.remove(entity);
		return memory.add(entity);
	}

	@Override
	public boolean remove(T entity) {
		if (memory.contains(entity))
			return memory.remove(entity);
		return false;
	}

	@Override
	public boolean remove(String id) {
		T entity = this.get(id).get();
		return this.remove(entity);
	}

}
