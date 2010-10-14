package actions;

import java.io.Serializable;

public class TurnRadarLeft extends Action implements Serializable {
	private static final long serialVersionUID = -4376836364354143470L;
	double degree;

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public TurnRadarLeft(double degree) {
		super();
		this.degree = degree;
	}
}
