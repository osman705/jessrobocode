package events;

import java.io.Serializable;

public class ScannedRobot extends Event implements Serializable{
	private static final long serialVersionUID = 6405539407744640519L;
	double bearing;
	double distance;
	double energy;
	double heading;
	String name;
	double velocity;
	public double getBearing() {
		return bearing;
	}
	public void setBearing(double bearing) {
		this.bearing = bearing;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public ScannedRobot(double bearing, double distance, double energy,
			double heading, String name, double velocity) {
		super();
		this.bearing = bearing;
		this.distance = distance;
		this.energy = energy;
		this.heading = heading;
		this.name = name;
		this.velocity = velocity;
	}
}
