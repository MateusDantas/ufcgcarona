package models;

import javax.persistence.Entity;

@Entity
public class Route extends Identity {

	public Location origin;
	public Location destination;
	
	public String validate() {
		return null;
	}
	
	public Location getOrigin() {
		return origin;
	}
	public void setOrigin(Location origin) {
		this.origin = origin;
	}
	public Location getDestination() {
		return destination;
	}
	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public boolean compare(Route route) {
		if (route == null)
			return false;
		return this.origin.compare(route.getOrigin()) && this.destination.compare(route.getDestination());
	}
	
}
