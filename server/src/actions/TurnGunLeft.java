package actions;

import java.io.Serializable;

public class TurnGunLeft extends Action implements Serializable {
	private static final long serialVersionUID = 2813080769399759975L;
	double degree;

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public TurnGunLeft(double degree) {
		super();
		this.degree = degree;
	}
}
