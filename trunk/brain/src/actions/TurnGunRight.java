package actions;

import java.io.Serializable;

public class TurnGunRight extends Action implements Serializable {
	private static final long serialVersionUID = -7389155508024979361L;
	double radians;

	public double getRadians() {
		return radians;
	}

	public void setRadians(double radians) {
		this.radians = radians;
	}

	public TurnGunRight(double radians) {
		super();
		this.radians = radians;
	}
}
