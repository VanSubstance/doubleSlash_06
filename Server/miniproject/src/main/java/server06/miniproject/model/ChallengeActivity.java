package server06.miniproject.model;


public class ChallengeActivity {

	private int chalactId;
	private int chalId;
	private String regdate;
	private String img;
	private int cnt;

	public ChallengeActivity(int chalactId, int chalId, String regdate, String img, int cnt) {
		super();
		this.chalactId = chalactId;
		this.chalId = chalId;
		this.regdate = regdate;
		this.img = img;
		this.cnt = cnt;
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "ChallengeActivity [chalactId=" + chalactId + ", chalId=" + chalId + ", regdate=" + regdate + ", img="
				+ img + ", cnt=" + cnt + "]";
	}

}
