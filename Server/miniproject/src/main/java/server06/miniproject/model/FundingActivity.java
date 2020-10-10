package server06.miniproject.model;

import java.util.Date;

public class FundingActivity {
	private int fund_act_id;
	private int fund_id;
	private int mem_id;
	private int point;
	private Date funddate;
	
	public FundingActivity(int fund_act_id, int fund_id, int mem_id, int point, Date funddate) {
		super();
		this.fund_act_id = fund_act_id;
		this.fund_id = fund_id;
		this.mem_id = mem_id;
		this.point = point;
		this.funddate = funddate;
	}

	public FundingActivity() {
		super();
	}

	public int getFund_act_id() {
		return fund_act_id;
	}

	public void setFund_act_id(int fund_act_id) {
		this.fund_act_id = fund_act_id;
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

	public Date getFunddate() {
		return funddate;
	}

	public void setFunddate(Date funddate) {
		this.funddate = funddate;
	}
	
}
