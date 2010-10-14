package events;

import java.io.Serializable;

public class RoundEnd extends Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3867795570544753108L;
	int round;
	int totalRounds;
	int turns;
	public RoundEnd(int round, int totalRounds, int turns) {
		super();
		this.round = round;
		this.totalRounds = totalRounds;
		this.turns = turns;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getTotalRounds() {
		return totalRounds;
	}
	public void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}
	public int getTurns() {
		return turns;
	}
	public void setTurns(int turns) {
		this.turns = turns;
	}
}
