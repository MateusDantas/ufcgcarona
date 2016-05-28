package repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import models.Entity;

public abstract class InMemoryRepository<T extends Entity> implements Repository<T> {

	private Set<T> memory = new HashSet<>();

	@Override
	public Optional<T> get(String id) {
		return memory.stream().filter(entity -> entity.getId().equals(id)).findFirst();
	}

	@Override
	public Set<T> query(Predicate<T> query) {
		return memory.stream().filter(query).collect(Collectors.toSet());
	}

	@Override
	public void save(T entity) {
		if (memory.contains(entity))
			memory.remove(entity);
		memory.add(entity);
	}

	@Override
	public void save(Collection<T> entities) {
		entities.forEach(this::save);
	}

	@Override
	public void remove(T entity) {
		if (memory.contains(entity))
			memory.remove(entity);
	}

	@Override
	public void remove(Collection<T> entities) {
		entities.forEach(this::remove);
	}

	@Override
	public void remove(String id) {
		this.get(id).ifPresent(this::remove);
	}

}
