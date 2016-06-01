package repository;

import java.util.Set;
import java.util.stream.Collectors;

import com.avaje.ebean.Ebean;

import models.Ride;

@Singleton
public class RideEbeanRepository extends EbeanRepository<Ride> {
	
	@Override
	public Set<Ride> get() {
		return Ride.find.all().stream().collect(Collectors.toSet());
	}

	@Override
	public boolean save(Ride entity) {
		Ebean.save(entity);
		return true;
	}

	@Override
	public boolean remove(Ride entity) {
		return Ebean.delete(entity);
	}
}
