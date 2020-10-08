package server06.miniproject.model;

import java.sql.Date;

public class ChallengeActivity {

	private int chalactId;
	private int chalId;
	private Date regdate;
	private String img;

	public ChallengeActivity(int chalactId, int chalId, Date regdate, String img) {
		super();
		this.chalactId = chalactId;
		this.chalId = chalId;
		this.regdate = regdate;
		this.img = img;
	}

	public int getChalactId() {
		return chalactId;
	}

	public void setChalactId(int chalactId) {
		this.chalactId = chalactId;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "ChallengeActivity [chalactId=" + chalactId + ", chalId=" + chalId + ", regdate=" + regdate + ", img="
				+ img + "]";
	}

}
