package events;

import java.io.Serializable;

public class HitWall extends Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3247264607442150714L;
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
