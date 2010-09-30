package events;

public class BulletHit {
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
