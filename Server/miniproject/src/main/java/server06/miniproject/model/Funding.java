package server06.miniproject.model;

import java.sql.Date;

public class Funding {
	private String fundingid;
	private String title;
	private String content;
	private Date startdate;
	private Date enddate;
	private int targetpoint;
	private String description;




	public Funding(String fundingid, String title, String content, Date startdate, Date enddate, int targetpoint,
			String description) {
		super();
		this.fundingid = fundingid;
		this.title = title;
		this.content = content;
		this.startdate = startdate;
		this.enddate = enddate;
		this.targetpoint = targetpoint;
		this.description = description;
	}

	public String getFundingid() {
		return fundingid;
	}

	public void setFundingid(String fundingid) {
		this.fundingid = fundingid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public int getTargetpoint() {
		return targetpoint;
	}

	public void setTargetpoint(int targetpoint) {
		this.targetpoint = targetpoint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Funding [fundingid=" + fundingid + ", title=" + title + ", content=" + content + ", startdate="
				+ startdate + ", enddate=" + enddate + ", targetpoint=" + targetpoint + ", description=" + description
				+ "]";
	}



}
