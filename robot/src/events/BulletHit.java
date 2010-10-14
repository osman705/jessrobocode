package events;

import java.io.Serializable;

public class BulletHit extends Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7807252159144889448L;
	double energy;
	public BulletHit(double energy, String name) {
		super();
		this.energy = energy;
		this.name = name;
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
	String name;
}
