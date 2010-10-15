package actions;

import java.io.Serializable;

public class TurnGunLeft extends Action implements Serializable {
	private static final long serialVersionUID = 2813080769399759975L;
	double radians;

	public double getRadians() {
		return radians;
	}

	public void setRadians(double radians) {
		this.radians = radians;
	}

	public TurnGunLeft(double radians) {
		super();
		this.radians = radians;
	}
}
