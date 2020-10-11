package server06.miniproject.model;

public class ChallengeFrame {
	private int ch_id;
	private int point;
	private String title;
	private String des;

	public ChallengeFrame(int ch_id, int point, String title, String des) {
		super();
		this.ch_id = ch_id;
		this.point = point;
		this.title = title;
		this.des = des;
	}

	public int getCh_id() {
		return ch_id;
	}

	public void setCh_id(int ch_id) {
		this.ch_id = ch_id;
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

	@Override
	public String toString() {
		return "ChallengeFrame [ch_id=" + ch_id + ", point=" + point + ", title=" + title + ", des=" + des + "]";
	}

}
