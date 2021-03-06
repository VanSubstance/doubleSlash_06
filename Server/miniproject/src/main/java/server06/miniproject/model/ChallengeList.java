package server06.miniproject.model;

import java.sql.Date;

public class ChallengeList {
	private int chalId;
	private Date regdate;
	private Date deadline;
	private int chalPoint;
	private String title;
	private String des;

	public ChallengeList(int chalId, Date regdate, Date deadline, int chalPoint, String title, String des) {
		super();
		this.chalId = chalId;
		this.regdate = regdate;
		this.deadline = deadline;
		this.chalPoint = chalPoint;
		this.title = title;
		this.des = des;
	}

	public int getChalId() {
		return chalId;
	}

	public void setChalId(int chalId) {
		this.chalId = chalId;
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

	public int getChalPoint() {
		return chalPoint;
	}

	public void setChalPoint(int chalPoint) {
		this.chalPoint = chalPoint;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return "ChallengeList [chalId=" + chalId + ", regdate=" + regdate + ", deadline=" + deadline + ", chalPoint="
				+ chalPoint + ", title=" + title + ", des=" + des + "]";
	}

}
