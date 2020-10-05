package server06.miniproject.model;

public class Challenge {
	private int id;
	private int point;
	private String title;
	private String des;
	
	public Challenge(int id, int point, String title, String des) {
		super();
		this.id = id;
		this.point = point;
		this.title = title;
		this.des = des;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Challenge [id=" + id + ", point=" + point + ", title=" + title + ", des=" + des + "]";
	}
	
	
	

}
