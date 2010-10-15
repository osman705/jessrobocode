package actions;

import java.io.Serializable;

public class TurnRight extends Action implements Serializable {
	private static final long serialVersionUID = 2248916969763347596L;
	double radians;

	public double getDistance() {
		return radians;
	}

	public void setDistance(double radians) {
		this.radians = radians;
	}

	public TurnRight(double radians) {
		super();
		this.radians = radians;
	}
}
