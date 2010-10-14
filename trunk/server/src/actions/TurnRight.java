package actions;

import java.io.Serializable;

public class TurnRight extends Action implements Serializable {
	private static final long serialVersionUID = 2248916969763347596L;
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
