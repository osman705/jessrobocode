package events;

import java.io.Serializable;

public class HitByBullet extends Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1659918153038753268L;
	double bearing;
	double heading;
	String name;
	double power;
	double velocity;
	public double getBearing() {
		return bearing;
	}
	public void setBearing(double bearing) {
		this.bearing = bearing;
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
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public HitByBullet(double bearing, double heading, String name,
			double power, double velocity) {
		super();
		this.bearing = bearing;
		this.heading = heading;
		this.name = name;
		this.power = power;
		this.velocity = velocity;
	}
	
}
