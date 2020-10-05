package server06.miniproject.model;

public class Member {
	private int id;
	private String nick;
	private int point;
	private int lat;
	private int lon;
	
	public Member(int id, String nick, int point, int lat, int lon) {
		super();
		this.id = id;
		this.nick = nick;
		this.point = point;
		this.lat = lat;
		this.lon = lon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public int getLon() {
		return lon;
	}
	public void setLon(int lon) {
		this.lon = lon;
	}
	@Override
	public String toString() {
		return "member [id=" + id + ", nick=" + nick + ", point=" + point + ", lat=" + lat + ", lon=" + lon + "]";
	}
	
	
}
