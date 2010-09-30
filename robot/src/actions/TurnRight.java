package actions;

import events.BulletHit;

public class TurnRight {
	double degree;

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public TurnRight(double degree) {
		super();
		this.degree = degree;
	}
}
