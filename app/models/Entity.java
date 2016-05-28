package models;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
	private final String id;
	
	public Entity() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        
        Entity entity = (Entity) obj;
        return this.getId() == entity.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
}
