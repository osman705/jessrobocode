package actions;

import java.io.Serializable;

public class Ahead extends Action implements Serializable {
	private static final long serialVersionUID = -8417110387555894805L;
	double distance;

	public Ahead(double distance) {
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
