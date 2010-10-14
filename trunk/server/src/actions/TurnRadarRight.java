package actions;

import java.io.Serializable;

public class TurnRadarRight extends Action implements Serializable {
	private static final long serialVersionUID = -3424748897605371015L;
	double degree;

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public TurnRadarRight(double degree) {
		super();
		this.degree = degree;
	}
}