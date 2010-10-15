package events;

import java.io.Serializable;

public class HitRobot extends Event implements Serializable{
	private static final long serialVersionUID = -2855337385836411174L;
	double bearing;
	double energy;
	String name;
	boolean isMyFault;
	
	public double getBearing() {
		return bearing;
	}
	public void setBearing(double bearing) {
		this.bearing = bearing;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMyFault() {
		return isMyFault;
	}
	public void setMyFault(boolean isMyFault) {
		this.isMyFault = isMyFault;
	}
	public HitRobot(double bearing, double energy, String name,
			boolean isMyFault) {
		super();
		this.bearing = bearing;
		this.energy = energy;
		this.name = name;
		this.isMyFault = isMyFault;
	}
}
