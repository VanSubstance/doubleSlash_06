package server06.miniproject.model;

public class Challenge {
	private int chalId;
	private int memId;
	private String regdate;
	private String deadline;
	private int chalfrId;
	private char completed;
	private int chalPoint;

	public Challenge(int chalId, int memId, String regdate, String deadline, int chalfrId, char completed,
			int chalPoint) {
		super();
		this.chalId = chalId;
		this.memId = memId;
		this.regdate = regdate;
		this.deadline = deadline;
		this.chalfrId = chalfrId;
		this.completed = completed;
		this.chalPoint = chalPoint;
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getChalfrId() {
		return chalfrId;
	}

	public void setChalfrId(int chalfrId) {
		this.chalfrId = chalfrId;
	}

	public char getCompleted() {
		return completed;
	}

	public void setCompleted(char completed) {
		this.completed = completed;
	}

	public int getChalPoint() {
		return chalPoint;
	}

	public void setChalPoint(int chalPoint) {
		this.chalPoint = chalPoint;
	}

	@Override
	public String toString() {
		return "Challenge [chalId=" + chalId + ", memId=" + memId + ", regdate=" + regdate + ", deadline=" + deadline
				+ ", chalfrId=" + chalfrId + ", completed=" + completed + ", chalPoint=" + chalPoint + "]";
	}

}
