package events;

import java.io.Serializable;

public class Status extends Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9101198214889855141L;
	String name;
	double energy;
	double gunHeading;
	double gunHeat;
	double heading;
	double radarHeading;
	int others;
	double velocity;
	double x;
	double y;
	public Status(String name, double energy, double gunHeading, double gunHeat,
			double heading, double radarHeading, int others, double velocity,
			double x, double y) {
		super();
		this.name = name;
		this.energy = energy;
		this.gunHeading = gunHeading;
		this.gunHeat = gunHeat;
		this.heading = heading;
		this.radarHeading = radarHeading;
		this.others = others;
		this.velocity = velocity;
		this.x = x;
		this.y = y;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public double getGunHeading() {
		return gunHeading;
	}
	public void setGunHeading(double gunHeading) {
		this.gunHeading = gunHeading;
	}
	public double getGunHeat() {
		return gunHeat;
	}
	public void setGunHeat(double gunHeat) {
		this.gunHeat = gunHeat;
	}
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public double getRadarHeading() {
		return radarHeading;
	}
	public void setRadarHeading(double radarHeading) {
		this.radarHeading = radarHeading;
	}
	public int getOthers() {
		return others;
	}
	public void setOthers(int others) {
		this.others = others;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
