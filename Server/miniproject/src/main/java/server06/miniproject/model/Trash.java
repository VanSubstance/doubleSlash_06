package server06.miniproject.model;

public class Trash {
	
	private int id;
	private String title;
	private String img;
	private String des;
	private String ctgr;
	
	
	public Trash(int id, String title, String img, String des, String ctgr) {
		super();
		this.id = id;
		this.title = title;
		this.img = img;
		this.des = des;
		this.ctgr = ctgr;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getCtgr() {
		return ctgr;
	}
	public void setCtgr(String ctgr) {
		this.ctgr = ctgr;
	}

	@Override
	public String toString() {
		return "Trash [id=" + id + ", title=" + title + ", img=" + img + ", des=" + des + ", ctgr=" + ctgr + "]";
	}
	
	

}
