package server06.miniproject.model;

public class ChallengeFrame {
	private int chalfrId;
	private int point;
	private String title;
	private String des;
	private int cnt;

	public ChallengeFrame(int chalfrId, int point, String title, String des, int cnt) {
		super();
		this.chalfrId = chalfrId;
		this.point = point;
		this.title = title;
		this.des = des;
		this.cnt = cnt;
	}

	public int getChalfrId() {
		return chalfrId;
	}

	public void setChalfrId(int chalfrId) {
		this.chalfrId = chalfrId;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "ChallengeFrame [chalfrId=" + chalfrId + ", point=" + point + ", title=" + title + ", des=" + des
				+ ", cnt=" + cnt + "]";
	}

}
