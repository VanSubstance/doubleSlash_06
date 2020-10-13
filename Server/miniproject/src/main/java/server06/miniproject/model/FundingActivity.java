package server06.miniproject.model;

import java.util.Date;

public class FundingActivity {
	private int fund_id;
	private int mem_id;
	private int point;
	private String funddate;
	private int acu_point;
	
	public FundingActivity(int fund_id, int mem_id, int point, String funddate, int acu_point) {
		super();
		this.fund_id = fund_id;
		this.mem_id = mem_id;
		this.point = point;
		this.funddate = funddate;
		this.acu_point = acu_point;
	}

	public FundingActivity() {
		super();
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public int getMem_id() {
		return mem_id;
	}

	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getFunddate() {
		return funddate;
	}

	public void setFunddate(String funddate) {
		this.funddate = funddate;
	}

	public int getAcu_point() {
		return acu_point;
	}

	public void setAcu_point(int acu_point) {
		this.acu_point = acu_point;
	}
	

}
