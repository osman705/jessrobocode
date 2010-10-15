package actions;

import java.io.Serializable;

public class TurnLeft extends Action implements Serializable {
	private static final long serialVersionUID = 3537029696100324206L;
	double radians;

	public double getDistance() {
		return radians;
	}

	public void setDistance(double radians) {
		this.radians = radians;
	}

	public TurnLeft(double radians) {
		super();
		this.radians = radians;
	}
}
