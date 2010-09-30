package events;

public class HitWall {
	double bearing;

	public double getBearing() {
		return bearing;
	}

	public void setBearing(double bearing) {
		this.bearing = bearing;
	}

	public HitWall(double bearing) {
		super();
		this.bearing = bearing;
	}
}
