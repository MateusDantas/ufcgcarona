package models;

public class Location extends Entity {

	public String name;
	public String streetName;
	public String sectionName;

	public String validate() {
		return null;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean compare(Location location) {
		if (location == null)
			return false;
		return this.name.equals(location.getName()) && this.streetName.equals(location.getStreetName())
				&& this.sectionName.equals(location.getSectionName());
	}

}
