package server06.miniproject.model;

import java.sql.Date;
import java.util.List;

public class Challenge {
	private int chalId;
	private int memId;
	private Date regdate;
	private Date deadline;
	private int chalfrId;
	private char favorite;
	private int chalPoint;
	
	private ChallengeFrame challengeFrames;

	public Challenge(int chalId, int memId, Date regdate, Date deadline, int chalfrId, char favorite, int chalPoint,
			ChallengeFrame challengeFrames) {
		super();
		this.chalId = chalId;
		this.memId = memId;
		this.regdate = regdate;
		this.deadline = deadline;
		this.chalfrId = chalfrId;
		this.favorite = favorite;
		this.chalPoint = chalPoint;
		this.challengeFrames = challengeFrames;
	}

	public int getChalId() {
		return chalId;
	}

	public void setChalId(int chalId) {
		this.chalId = chalId;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public int getChalfrId() {
		return chalfrId;
	}

	public void setChalfrId(int chalfrId) {
		this.chalfrId = chalfrId;
	}

	public char getFavorite() {
		return favorite;
	}

	public void setFavorite(char favorite) {
		this.favorite = favorite;
	}

	public int getChalPoint() {
		return chalPoint;
	}

	public void setChalPoint(int chalPoint) {
		this.chalPoint = chalPoint;
	}

	public ChallengeFrame getChallengeFrames() {
		return challengeFrames;
	}

	public void setChallengeFrames(ChallengeFrame challengeFrames) {
		this.challengeFrames = challengeFrames;
	}

	@Override
	public String toString() {
		return "Challenge [chalId=" + chalId + ", memId=" + memId + ", regdate=" + regdate + ", deadline=" + deadline
				+ ", chalfrId=" + chalfrId + ", favorite=" + favorite + ", chalPoint=" + chalPoint
				+ ", challengeFrames=" + challengeFrames + "]";
	}




}
