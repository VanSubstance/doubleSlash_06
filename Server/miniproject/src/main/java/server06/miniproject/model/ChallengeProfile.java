package server06.miniproject.model;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ChallengeProfile {
	private String ch_id;
	private String mem_id;
	private int ch_point;
	private String ch_title;
	private String ch_content;
	private Date ch_regdate;
	private Date ch_deadline;
	private float ch_lat;
	private float ch_lon;
	private char ch_success;
	
	public ChallengeProfile(String ch_id, String mem_id, int ch_point, String ch_title, String ch_content,
			Date ch_regdate, Date ch_deadline, float ch_lat, float ch_lon, char ch_success) {
		super();
		this.ch_id = ch_id;
		this.mem_id = mem_id;
		this.ch_point = ch_point;
		this.ch_title = ch_title;
		this.ch_content = ch_content;
		this.ch_regdate = ch_regdate;
		this.ch_deadline = ch_deadline;
		this.ch_lat = ch_lat;
		this.ch_lon = ch_lon;
		this.ch_success = ch_success;
	}
	public ChallengeProfile() {
		super();
	}
	public String getCh_id() {
		return ch_id;
	}
	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getCh_point() {
		return ch_point;
	}
	public void setCh_point(int ch_point) {
		this.ch_point = ch_point;
	}
	public String getCh_title() {
		return ch_title;
	}
	public void setCh_title(String ch_title) {
		this.ch_title = ch_title;
	}
	public String getCh_content() {
		return ch_content;
	}
	public void setCh_content(String ch_content) {
		this.ch_content = ch_content;
	}
	public Date getCh_regdate() {
		return ch_regdate;
	}
	public void setCh_regdate(Date ch_regdate) {
		this.ch_regdate = ch_regdate;
	}
	public Date getCh_deadline() {
		return ch_deadline;
	}
	public void setCh_deadline(Date ch_deadline) {
		this.ch_deadline = ch_deadline;
	}
	public float getCh_lat() {
		return ch_lat;
	}
	public void setCh_lat(float ch_lat) {
		this.ch_lat = ch_lat;
	}
	public float getCh_lon() {
		return ch_lon;
	}
	public void setCh_lon(float ch_lon) {
		this.ch_lon = ch_lon;
	}
	public char getCh_success() {
		return ch_success;
	}
	public void setCh_success(char ch_success) {
		this.ch_success = ch_success;
	}
	
}
