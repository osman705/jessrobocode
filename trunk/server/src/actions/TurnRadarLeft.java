package actions;

import java.io.Serializable;

public class TurnRadarLeft extends Action implements Serializable {
	private static final long serialVersionUID = -4376836364354143470L;
	double radians;

	public double getRadians() {
		return radians;
	}

	public void setRadians(double radians) {
		this.radians = radians;
	}

	public TurnRadarLeft(double radians) {
		super();
		this.radians = radians;
	}
}
