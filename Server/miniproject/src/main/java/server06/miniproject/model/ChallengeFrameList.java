package server06.miniproject.model;

public class ChallengeFrameList {
	
	private String title;
	private String des;
	private int point;
	
	public ChallengeFrameList(String title, String des, int point) {
		super();
		this.title = title;
		this.des = des;
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "ChallengeFrameList [title=" + title + ", des=" + des + ", point=" + point + "]";
	}
	
	

}
