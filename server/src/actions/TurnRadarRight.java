package actions;

import java.io.Serializable;

public class TurnRadarRight extends Action implements Serializable {
	private static final long serialVersionUID = -3424748897605371015L;
	double radians;

	public double getRadians() {
		return radians;
	}

	public void setRadians(double radians) {
		this.radians = radians;
	}

	public TurnRadarRight(double radians) {
		super();
		this.radians = radians;
	}
}
