package actions;

import java.io.Serializable;

public class TurnLeft extends Action implements Serializable {
	private static final long serialVersionUID = 3537029696100324206L;
	double degree;

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public TurnLeft(double degree) {
		super();
		this.degree = degree;
	}
}
