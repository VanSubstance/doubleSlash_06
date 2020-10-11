package server06.miniproject.model;

public class Member {
	private int id;
	private String nick;
	private int point;
	private float lat;
	private float lon;

	public Member(int id, String nick, int point, float lat, float lon) {
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

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", nick=" + nick + ", point=" + point + ", lat=" + lat + ", lon=" + lon + "]";
	}

}
