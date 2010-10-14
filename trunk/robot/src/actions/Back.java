package actions;

import java.io.Serializable;

public class Back extends Action implements Serializable {
	private static final long serialVersionUID = 296179462559540816L;
	double distance;

	public Back(double distance) {
		super();
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
