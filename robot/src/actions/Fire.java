package actions;

import java.io.Serializable;

public class Fire extends Action implements Serializable {
	private static final long serialVersionUID = -6298868250045648177L;
	double power;
	public Fire(double power) {
		this.power = power;
	}
	
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
}
